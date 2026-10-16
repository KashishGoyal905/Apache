package com.aftermarket.backend.workflow;

import static org.assertj.core.api.Assertions.assertThat;

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
class WorkflowFacadeNullVariableTest {

    @Autowired
    private WorkflowFacade facade;

    @Test
    void completesAndReadsBackWithNullVariableValues() {
        // No durations/groups passed on purpose: the facade fills in the configured defaults.
        Map<String, Object> startVariables = new HashMap<>();
        startVariables.put("requestId", "req-null-1");
        startVariables.put("entityType", "CCR");
        startVariables.put("submittedBy", "employee1");
        startVariables.put("comment", null);

        ProcessInstanceResponse started =
                facade.start(new StartProcessRequest("ccrApproval", "req-null-1", startVariables));
        assertThat(started.ended()).isFalse();
        assertThat(started.activeTaskKeys()).isNotEmpty();

        List<TaskResponse> tasks = facade.listTasks(null, null, started.processInstanceId());
        assertThat(tasks).hasSize(1);

        Map<String, Object> completionVariables = new HashMap<>();
        completionVariables.put("approved", false);
        completionVariables.put("comment", null);

        ProcessInstanceResponse completed =
                facade.complete(tasks.get(0).taskId(), new CompleteTaskRequest("manager1", completionVariables));
        assertThat(completed.ended()).isTrue();
        assertThat(completed.variables().get("approved")).isEqualTo(false);
    }
}
