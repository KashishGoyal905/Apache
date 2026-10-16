package com.aftermarket.backend.workflow;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.kie.kogito.Model;
import org.kie.kogito.auth.IdentityProvider;
import org.kie.kogito.auth.IdentityProviders;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.process.ProcessInstanceReadMode;
import org.kie.kogito.process.Processes;
import org.kie.kogito.usertask.UserTaskInstance;
import org.kie.kogito.usertask.UserTasks;
import org.kie.kogito.usertask.impl.lifecycle.DefaultUserTaskLifeCycle;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.aftermarket.backend.config.AftermarketProperties;
import com.aftermarket.backend.workflow.dto.CompleteTaskRequest;
import com.aftermarket.backend.workflow.dto.HistoryEntry;
import com.aftermarket.backend.workflow.dto.ProcessInstanceResponse;
import com.aftermarket.backend.workflow.dto.StartProcessRequest;
import com.aftermarket.backend.workflow.dto.TaskResponse;

/**
 * The only class that talks to the Apache KIE (Kogito) engine. Everything above it (services,
 * controllers) works with the small DTOs in {@code workflow.dto}.
 */
@Service
public class WorkflowFacade {

    /** Groups known to the app; used to look tasks up when we only have a process instance id. */
    private static final List<String> ALL_GROUPS = List.of("employees", "supervisors", "finance");

    /** Task outputs defined on the approval user tasks in the BPMN. */
    private static final List<String> TASK_OUTPUTS = List.of("approved", "decision", "comment", "lastCompletedBy");

    private static final List<String> TASK_KEYS = List.of("managerApproval", "financeApproval");

    /** Name used only to query "which tasks does this group see". Never performs an action. */
    private static final String GROUP_PROBE_USER = "group-probe";

    private final Processes processes;
    private final UserTasks userTasks;
    private final AftermarketProperties properties;

    public WorkflowFacade(Processes processes, UserTasks userTasks, AftermarketProperties properties) {
        this.processes = processes;
        this.userTasks = userTasks;
        this.properties = properties;
    }

    // -----------------------------------------------------------------------
    // Process operations
    // -----------------------------------------------------------------------

    public ProcessInstanceResponse start(StartProcessRequest request) {
        if (request.processDefinitionKey() == null || request.processDefinitionKey().isBlank()) {
            throw new IllegalArgumentException("processDefinitionKey is required");
        }
        Process<? extends Model> process = processes.processById(request.processDefinitionKey());
        if (process == null) {
            throw new IllegalArgumentException("Unknown process: " + request.processDefinitionKey());
        }

        Map<String, Object> variables = new HashMap<>();
        variables.put("supervisorGroup", "supervisors");
        variables.put("financeGroup", "finance");
        variables.put("managerReminderDuration", properties.managerReminderDuration());
        variables.put("managerEscalationDuration", properties.managerEscalationDuration());
        if (request.variables() != null) {
            request.variables().forEach((k, v) -> {
                if (v != null) {
                    variables.put(k, v);
                }
            });
        }

        Model model = process.createModel();
        model.fromMap(variables);

        ProcessInstance<? extends Model> instance;
        if (notBlank(request.businessKey())) {
            instance = process.createInstance(request.businessKey(), model);
        } else {
            instance = process.createInstance(model);
        }
        instance.start();
        if (instance.status() == org.kie.api.runtime.process.ProcessInstance.STATE_ERROR) {
            String reason = instance.error().map(e -> e.errorMessage()).orElse("unknown error");
            throw new IllegalStateException("Process " + request.processDefinitionKey() + " failed to start: " + reason);
        }
        return toResponse(instance);
    }

    public ProcessInstanceResponse getInstance(String processInstanceId) {
        parseId(processInstanceId);
        Optional<Process<? extends Model>> process = processes.processByProcessInstanceId(processInstanceId);
        if (process.isPresent()) {
            Optional<? extends ProcessInstance<? extends Model>> instance =
                    process.get().instances().findById(processInstanceId, ProcessInstanceReadMode.READ_ONLY);
            if (instance.isPresent()) {
                return toResponse(instance.get());
            }
        }
        // Kogito removes finished instances from its store, so "not found" means it already ended.
        return new ProcessInstanceResponse(processInstanceId, null, "ccrApproval", true, Map.of(), List.of());
    }

    /** Detailed engine history is intentionally not kept; the app's own ActivityLog is the audit trail. */
    public List<HistoryEntry> getHistory(String processInstanceId) {
        return List.of();
    }

    // -----------------------------------------------------------------------
    // Task listing / querying
    // -----------------------------------------------------------------------

    public List<TaskResponse> listTasks(String assignee, String candidateGroup, String processInstanceId) {
        List<UserTaskInstance> found = new ArrayList<>();
        if (notBlank(processInstanceId)) {
            for (UserTaskInstance task : visibleToAllGroups()) {
                if (processInstanceId.equals(task.getProcessInfo().getProcessInstanceId())) {
                    found.add(task);
                }
            }
        } else if (notBlank(assignee)) {
            found.addAll(userTasks.instances().findByIdentity(IdentityProviders.of(assignee, List.of())));
        } else if (notBlank(candidateGroup)) {
            found.addAll(userTasks.instances().findByIdentity(IdentityProviders.of(GROUP_PROBE_USER, List.of(candidateGroup))));
        } else {
            found.addAll(visibleToAllGroups());
        }

        Map<String, TaskResponse> byId = new LinkedHashMap<>();
        for (UserTaskInstance task : found) {
            if (isOpen(task)) {
                byId.put(task.getId(), toTaskResponse(task));
            }
        }
        return new ArrayList<>(byId.values());
    }

    public TaskResponse getTask(String taskId) {
        return toTaskResponse(findTask(taskId));
    }

    // -----------------------------------------------------------------------
    // Task actions
    // -----------------------------------------------------------------------

    /**
     * Completes a task as {@code request.userId()}. Authorization is enforced by the caller
     * (TaskAppService); here the user acts with the task's own candidate groups so the engine's
     * lifecycle permission check passes. Group tasks are claimed first (the engine only allows
     * completing a reserved task).
     */
    public ProcessInstanceResponse complete(String taskId, CompleteTaskRequest request) {
        UserTaskInstance task = findTask(taskId);
        String processInstanceId = task.getProcessInfo().getProcessInstanceId();
        String userId = request.userId();
        if (!notBlank(userId)) {
            throw new IllegalArgumentException("userId is required to complete a task");
        }
        IdentityProvider identity = identityFor(userId, task);

        if (DefaultUserTaskLifeCycle.ACTIVE.equals(task.getStatus())) {
            task.transition(DefaultUserTaskLifeCycle.CLAIM, Map.of(), identity);
        }

        Map<String, Object> variables = request.variables() == null ? new HashMap<>() : new HashMap<>(request.variables());
        variables.put("lastCompletedBy", userId);
        for (String key : TASK_OUTPUTS) {
            Object value = variables.get(key);
            if (value != null) {
                task.setOutput(key, value);
            }
        }
        task.transition(DefaultUserTaskLifeCycle.COMPLETE, Map.of(), identity);

        ProcessInstanceResponse after = getInstance(processInstanceId);
        if (after.ended() && after.variables().isEmpty()) {
            Map<String, Object> finalVars = new HashMap<>();
            for (String key : TASK_OUTPUTS) {
                if (variables.get(key) != null) {
                    finalVars.put(key, variables.get(key));
                }
            }
            return new ProcessInstanceResponse(
                    processInstanceId, after.businessKey(), after.processDefinitionKey(), true, finalVars, List.of());
        }
        return after;
    }

    public TaskResponse claim(String taskId, String userId) {
        if (!notBlank(userId)) {
            throw new IllegalArgumentException("userId is required to claim a task");
        }
        UserTaskInstance task = findTask(taskId);
        if (DefaultUserTaskLifeCycle.ACTIVE.equals(task.getStatus())) {
            task.transition(DefaultUserTaskLifeCycle.CLAIM, Map.of(), identityFor(userId, task));
        }
        return getTask(taskId);
    }

    // -----------------------------------------------------------------------
    // Private helpers
    // -----------------------------------------------------------------------

    private UserTaskInstance findTask(String taskId) {
        return userTasks.instances().findById(taskId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found: " + taskId));
    }

    private static IdentityProvider identityFor(String userId, UserTaskInstance task) {
        return IdentityProviders.of(userId, new ArrayList<>(task.getPotentialGroups()));
    }

    private List<UserTaskInstance> visibleToAllGroups() {
        return userTasks.instances().findByIdentity(IdentityProviders.of(GROUP_PROBE_USER, ALL_GROUPS));
    }

    private static boolean isOpen(UserTaskInstance task) {
        return DefaultUserTaskLifeCycle.ACTIVE.equals(task.getStatus())
                || DefaultUserTaskLifeCycle.RESERVED.equals(task.getStatus());
    }

    private ProcessInstanceResponse toResponse(ProcessInstance<? extends Model> instance) {
        boolean ended = instance.status() != org.kie.api.runtime.process.ProcessInstance.STATE_ACTIVE;
        Map<String, Object> vars = new HashMap<>();
        Model model = instance.variables();
        if (model != null && model.toMap() != null) {
            model.toMap().forEach((k, v) -> {
                if (v != null) {
                    vars.put(k, v);
                }
            });
        }
        List<String> activeKeys = new ArrayList<>();
        if (!ended) {
            for (UserTaskInstance task : visibleToAllGroups()) {
                if (isOpen(task) && instance.id().equals(task.getProcessInfo().getProcessInstanceId())) {
                    String key = taskKey(task);
                    if (!activeKeys.contains(key)) {
                        activeKeys.add(key);
                    }
                }
            }
        }
        return new ProcessInstanceResponse(
                instance.id(), instance.businessKey(), instance.process().id(), ended, vars, activeKeys);
    }

    private TaskResponse toTaskResponse(UserTaskInstance task) {
        Map<String, Object> inputs = task.getInputs() == null ? Map.of() : task.getInputs();
        Object requestId = inputs.get("requestId");
        String key = taskKey(task);
        return new TaskResponse(
                task.getId(),
                task.getTaskName(),
                key,
                task.getProcessInfo().getProcessInstanceId(),
                task.getProcessInfo().getProcessId(),
                requestId == null ? null : String.valueOf(requestId),
                task.getActualOwner(),
                new ArrayList<>(task.getPotentialGroups()),
                key,
                new HashMap<>(inputs),
                (Instant) null);
    }

    /** Stable task key ("managerApproval" / "financeApproval"), taken from the BPMN `taskKey` input. */
    private static String taskKey(UserTaskInstance task) {
        Object fromInput = task.getInputs() == null ? null : task.getInputs().get("taskKey");
        if (fromInput != null) {
            return String.valueOf(fromInput);
        }
        String normalized = task.getTaskName() == null ? "" : task.getTaskName().replaceAll("\\s+", "");
        for (String key : TASK_KEYS) {
            if (key.equalsIgnoreCase(normalized)) {
                return key;
            }
        }
        return task.getTaskName();
    }

    private static boolean notBlank(String value) {
        return value != null && !value.isBlank();
    }

    private static void parseId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Invalid process/task id: " + id);
        }
    }
}
