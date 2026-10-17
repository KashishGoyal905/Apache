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

import org.kie.kogito.MapInput;
import org.kie.kogito.MapInputId;
import org.kie.kogito.MapOutput;
import java.util.Map;
import java.util.HashMap;
import org.kie.kogito.MappableToModel;
import org.kie.kogito.Model;

@org.kie.kogito.codegen.Generated(value = "kogito-codegen", reference = "ccrApproval", name = "CcrApproval", hidden = true)
public class CcrApprovalModelOutput implements org.kie.kogito.Model, MapInput, MapInputId, MapOutput, MappableToModel<CcrApprovalModel> {

    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    @org.kie.kogito.codegen.VariableInfo(tags = "")
    @com.fasterxml.jackson.annotation.JsonProperty(value = "submittedBy")
    @jakarta.validation.Valid()
    private java.lang.String submittedBy;

    public java.lang.String getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(java.lang.String submittedBy) {
        this.submittedBy = submittedBy;
    }

    @org.kie.kogito.codegen.VariableInfo(tags = "")
    @com.fasterxml.jackson.annotation.JsonProperty(value = "approved")
    @jakarta.validation.Valid()
    private java.lang.Boolean approved;

    public java.lang.Boolean getApproved() {
        return approved;
    }

    public void setApproved(java.lang.Boolean approved) {
        this.approved = approved;
    }

    @org.kie.kogito.codegen.VariableInfo(tags = "")
    @com.fasterxml.jackson.annotation.JsonProperty(value = "managerEscalationDuration")
    @jakarta.validation.Valid()
    private java.lang.String managerEscalationDuration;

    public java.lang.String getManagerEscalationDuration() {
        return managerEscalationDuration;
    }

    public void setManagerEscalationDuration(java.lang.String managerEscalationDuration) {
        this.managerEscalationDuration = managerEscalationDuration;
    }

    @org.kie.kogito.codegen.VariableInfo(tags = "")
    @com.fasterxml.jackson.annotation.JsonProperty(value = "decision")
    @jakarta.validation.Valid()
    private java.lang.String decision;

    public java.lang.String getDecision() {
        return decision;
    }

    public void setDecision(java.lang.String decision) {
        this.decision = decision;
    }

    @org.kie.kogito.codegen.VariableInfo(tags = "")
    @com.fasterxml.jackson.annotation.JsonProperty(value = "requestId")
    @jakarta.validation.Valid()
    private java.lang.String requestId;

    public java.lang.String getRequestId() {
        return requestId;
    }

    public void setRequestId(java.lang.String requestId) {
        this.requestId = requestId;
    }

    @org.kie.kogito.codegen.VariableInfo(tags = "")
    @com.fasterxml.jackson.annotation.JsonProperty(value = "entityType")
    @jakarta.validation.Valid()
    private java.lang.String entityType;

    public java.lang.String getEntityType() {
        return entityType;
    }

    public void setEntityType(java.lang.String entityType) {
        this.entityType = entityType;
    }

    @org.kie.kogito.codegen.VariableInfo(tags = "")
    @com.fasterxml.jackson.annotation.JsonProperty(value = "financeGroup")
    @jakarta.validation.Valid()
    private java.lang.String financeGroup;

    public java.lang.String getFinanceGroup() {
        return financeGroup;
    }

    public void setFinanceGroup(java.lang.String financeGroup) {
        this.financeGroup = financeGroup;
    }

    @org.kie.kogito.codegen.VariableInfo(tags = "")
    @com.fasterxml.jackson.annotation.JsonProperty(value = "employeeGroup")
    @jakarta.validation.Valid()
    private java.lang.String employeeGroup;

    public java.lang.String getEmployeeGroup() {
        return employeeGroup;
    }

    public void setEmployeeGroup(java.lang.String employeeGroup) {
        this.employeeGroup = employeeGroup;
    }

    @org.kie.kogito.codegen.VariableInfo(tags = "")
    @com.fasterxml.jackson.annotation.JsonProperty(value = "lastCompletedBy")
    @jakarta.validation.Valid()
    private java.lang.String lastCompletedBy;

    public java.lang.String getLastCompletedBy() {
        return lastCompletedBy;
    }

    public void setLastCompletedBy(java.lang.String lastCompletedBy) {
        this.lastCompletedBy = lastCompletedBy;
    }

    @org.kie.kogito.codegen.VariableInfo(tags = "")
    @com.fasterxml.jackson.annotation.JsonProperty(value = "comment")
    @jakarta.validation.Valid()
    private java.lang.String comment;

    public java.lang.String getComment() {
        return comment;
    }

    public void setComment(java.lang.String comment) {
        this.comment = comment;
    }

    @org.kie.kogito.codegen.VariableInfo(tags = "")
    @com.fasterxml.jackson.annotation.JsonProperty(value = "managerReminderDuration")
    @jakarta.validation.Valid()
    private java.lang.String managerReminderDuration;

    public java.lang.String getManagerReminderDuration() {
        return managerReminderDuration;
    }

    public void setManagerReminderDuration(java.lang.String managerReminderDuration) {
        this.managerReminderDuration = managerReminderDuration;
    }

    @org.kie.kogito.codegen.VariableInfo(tags = "")
    @com.fasterxml.jackson.annotation.JsonProperty(value = "supervisorGroup")
    @jakarta.validation.Valid()
    private java.lang.String supervisorGroup;

    public java.lang.String getSupervisorGroup() {
        return supervisorGroup;
    }

    public void setSupervisorGroup(java.lang.String supervisorGroup) {
        this.supervisorGroup = supervisorGroup;
    }

    @Override()
    public CcrApprovalModel toModel() {
        CcrApprovalModel result = new CcrApprovalModel();
        result.setId(getId());
        result.setSubmittedBy(getSubmittedBy());
        result.setApproved(getApproved());
        result.setManagerEscalationDuration(getManagerEscalationDuration());
        result.setDecision(getDecision());
        result.setRequestId(getRequestId());
        result.setEntityType(getEntityType());
        result.setFinanceGroup(getFinanceGroup());
        result.setEmployeeGroup(getEmployeeGroup());
        result.setLastCompletedBy(getLastCompletedBy());
        result.setComment(getComment());
        result.setManagerReminderDuration(getManagerReminderDuration());
        result.setSupervisorGroup(getSupervisorGroup());
        return result;
    }
}
