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

import java.util.HashMap;
import java.util.Map;
import org.kie.kogito.MapOutput;
//Task output for user task 'Finance approval' in process 'ccrApproval'
import org.kie.kogito.UserTask;
import org.kie.kogito.UserTaskParam.ParamType;
import org.kie.kogito.UserTaskParam;

@UserTask(taskName = "finance approval", processName = "ccrApproval")
public class CcrApproval_financeApproval_TaskOutput implements MapOutput {

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> params = new HashMap<>();
        params.put("approved", this.approved);
        params.put("decision", this.decision);
        params.put("lastCompletedBy", this.lastCompletedBy);
        params.put("comment", this.comment);
        return params;
    }

    public static CcrApproval_financeApproval_TaskOutput fromMap(Map<String, Object> params) {
        com.aftermarket.backend.workflow.process.CcrApproval_financeApproval_TaskOutput result = new CcrApproval_financeApproval_TaskOutput();
        result.approved = (java.lang.Boolean) params.get("approved");
        result.decision = (java.lang.String) params.get("decision");
        result.lastCompletedBy = (java.lang.String) params.get("lastCompletedBy");
        result.comment = (java.lang.String) params.get("comment");
        return result;
    }

    @UserTaskParam(value = ParamType.OUTPUT)
    private java.lang.Boolean approved;

    public java.lang.Boolean getApproved() {
        return approved;
    }

    public void setApproved(java.lang.Boolean approved) {
        this.approved = approved;
    }

    @UserTaskParam(value = ParamType.OUTPUT)
    private java.lang.String decision;

    public java.lang.String getDecision() {
        return decision;
    }

    public void setDecision(java.lang.String decision) {
        this.decision = decision;
    }

    @UserTaskParam(value = ParamType.OUTPUT)
    private java.lang.String lastCompletedBy;

    public java.lang.String getLastCompletedBy() {
        return lastCompletedBy;
    }

    public void setLastCompletedBy(java.lang.String lastCompletedBy) {
        this.lastCompletedBy = lastCompletedBy;
    }

    @UserTaskParam(value = ParamType.OUTPUT)
    private java.lang.String comment;

    public java.lang.String getComment() {
        return comment;
    }

    public void setComment(java.lang.String comment) {
        this.comment = comment;
    }
}
