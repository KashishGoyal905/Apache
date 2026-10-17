/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.aftermarket.backend.workflow.process;

import java.util.Map;
//Task input for user task 'Finance approval' in process 'ccrApproval'
import org.kie.kogito.UserTask;
import org.kie.kogito.UserTaskParam.ParamType;
import org.kie.kogito.UserTaskParam;

@UserTask(taskName = "finance approval", processName = "ccrApproval")
public class CcrApproval_financeApproval_TaskInput {

    public static CcrApproval_financeApproval_TaskInput fromMap(Map<String, Object> params) {
        CcrApproval_financeApproval_TaskInput item = new CcrApproval_financeApproval_TaskInput();
        item.taskKey = (java.lang.String) params.get("taskKey");
        item.requestId = (java.lang.String) params.get("requestId");
        return item;
    }

    @UserTaskParam(value = ParamType.INPUT)
    private java.lang.String taskKey;

    public java.lang.String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(java.lang.String taskKey) {
        this.taskKey = taskKey;
    }

    @UserTaskParam(value = ParamType.INPUT)
    private java.lang.String requestId;

    public java.lang.String getRequestId() {
        return requestId;
    }

    public void setRequestId(java.lang.String requestId) {
        this.requestId = requestId;
    }
}
