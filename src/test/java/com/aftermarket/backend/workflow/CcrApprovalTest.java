package com.aftermarket.backend.workflow;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.aftermarket.backend.workflow.dto.CompleteTaskRequest;
import com.aftermarket.backend.workflow.dto.ProcessInstanceResponse;
import com.aftermarket.backend.workflow.dto.StartProcessRequest;
import com.aftermarket.backend.workflow.dto.TaskResponse;

@SpringBootTest
class CcrApprovalTest {

    @Autowired
    private WorkflowFacade facade;

    private ProcessInstanceResponse startCcr(String businessKey, String reminder, String escalation) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("requestId", businessKey);
        variables.put("businessKey", businessKey);
        variables.put("entityType", "CCR");
        variables.put("submittedBy", "employee1");
        variables.put("employeeGroup", "employees");
        variables.put("supervisorGroup", "supervisors");
        variables.put("financeGroup", "finance");
        variables.put("managerReminderDuration", reminder);
        variables.put("managerEscalationDuration", escalation);
        return facade.start(new StartProcessRequest("ccrApproval", businessKey, variables));
    }

    private TaskResponse openTask(String processInstanceId) {
        List<TaskResponse> tasks = facade.listTasks(null, null, processInstanceId);
        assertThat(tasks).hasSize(1);
        return tasks.get(0);
    }

    private ProcessInstanceResponse decide(TaskResponse task, String userId, boolean approved) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("approved", approved);
        variables.put("decision", approved ? "APPROVED" : "REJECTED");
        return facade.complete(task.taskId(), new CompleteTaskRequest(userId, variables));
    }

    @Test
    void submittedCcrRoutesManagerThenFinance() {
        ProcessInstanceResponse started = startCcr("CCR-TEST-0001", "PT48H", "PT120H");
        assertThat(started.ended()).isFalse();
        assertThat(started.activeTaskKeys()).containsExactly("managerApproval");

        TaskResponse managerTask = openTask(started.processInstanceId());
        assertThat(managerTask.name()).isEqualToIgnoringCase("Manager approval");
        assertThat(managerTask.taskDefinitionKey()).isEqualTo("managerApproval");
        assertThat(managerTask.businessKey()).isEqualTo("CCR-TEST-0001");
        assertThat(managerTask.candidateGroups()).contains("supervisors");

        // visible to the supervisors group, not to finance
        assertThat(facade.listTasks(null, "supervisors", null)).extracting(TaskResponse::taskId).contains(managerTask.taskId());
        assertThat(facade.listTasks(null, "finance", null)).extracting(TaskResponse::taskId).doesNotContain(managerTask.taskId());

        ProcessInstanceResponse afterManager = decide(managerTask, "manager1", true);
        assertThat(afterManager.ended()).isFalse();
        assertThat(afterManager.activeTaskKeys()).containsExactly("financeApproval");

        TaskResponse financeTask = openTask(started.processInstanceId());
        assertThat(financeTask.taskDefinitionKey()).isEqualTo("financeApproval");
        assertThat(financeTask.candidateGroups()).contains("finance");

        ProcessInstanceResponse completed = decide(financeTask, "finance1", true);
        assertThat(completed.ended()).isTrue();
        assertThat(completed.variables().get("approved")).isEqualTo(true);
    }

    @Test
    void managerRejectionSkipsFinanceAndEnds() {
        ProcessInstanceResponse started = startCcr("CCR-TEST-0002", "PT48H", "PT120H");
        TaskResponse managerTask = openTask(started.processInstanceId());

        ProcessInstanceResponse rejected = decide(managerTask, "manager1", false);
        assertThat(rejected.ended()).isTrue();
        assertThat(rejected.variables().get("approved")).isEqualTo(false);
        assertThat(facade.listTasks(null, null, started.processInstanceId())).isEmpty();
    }

    @Test
    void financeRejectionEndsAsRejected() {
        ProcessInstanceResponse started = startCcr("CCR-TEST-0003", "PT48H", "PT120H");
        decide(openTask(started.processInstanceId()), "manager1", true);

        ProcessInstanceResponse rejected = decide(openTask(started.processInstanceId()), "finance1", false);
        assertThat(rejected.ended()).isTrue();
        assertThat(rejected.variables().get("approved")).isEqualTo(false);
    }

    @Test
    void managerEscalationTimerEndsTheRequest() {
        ProcessInstanceResponse started = startCcr("CCR-TEST-0004", "PT1S", "PT3S");
        assertThat(started.ended()).isFalse();

        // escalation interrupts the manager task and finalizes (rejected)
        await().atMost(Duration.ofSeconds(30)).untilAsserted(() ->
                assertThat(facade.getInstance(started.processInstanceId()).ended()).isTrue());
        assertThat(facade.listTasks(null, null, started.processInstanceId())).isEmpty();
    }
}
