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
//Task input for user task 'Notify approval' in process 'ccrApproval'
import org.kie.kogito.UserTask;
import org.kie.kogito.UserTaskParam.ParamType;
import org.kie.kogito.UserTaskParam;

@UserTask(taskName = "Notify approval", processName = "ccrApproval")
public class CcrApproval_notifyOutcome_TaskInput {

    public static CcrApproval_notifyOutcome_TaskInput fromMap(Map<String, Object> params) {
        CcrApproval_notifyOutcome_TaskInput item = new CcrApproval_notifyOutcome_TaskInput();
        item.businessKey = (java.lang.String) params.get("businessKey");
        item.Interface = (java.lang.String) params.get("Interface");
        item.Operation = (java.lang.String) params.get("Operation");
        item.interfaceImplementationRef = (java.lang.String) params.get("interfaceImplementationRef");
        item.operationImplementationRef = (java.lang.String) params.get("operationImplementationRef");
        item.implementation = (java.lang.String) params.get("implementation");
        return item;
    }

    @UserTaskParam(value = ParamType.INPUT)
    private java.lang.String businessKey;

    public java.lang.String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(java.lang.String businessKey) {
        this.businessKey = businessKey;
    }

    @UserTaskParam(value = ParamType.INPUT)
    private java.lang.String Interface;

    public java.lang.String getInterface() {
        return Interface;
    }

    public void setInterface(java.lang.String Interface) {
        this.Interface = Interface;
    }

    @UserTaskParam(value = ParamType.INPUT)
    private java.lang.String Operation;

    public java.lang.String getOperation() {
        return Operation;
    }

    public void setOperation(java.lang.String Operation) {
        this.Operation = Operation;
    }

    @UserTaskParam(value = ParamType.INPUT)
    private java.lang.String interfaceImplementationRef;

    public java.lang.String getInterfaceImplementationRef() {
        return interfaceImplementationRef;
    }

    public void setInterfaceImplementationRef(java.lang.String interfaceImplementationRef) {
        this.interfaceImplementationRef = interfaceImplementationRef;
    }

    @UserTaskParam(value = ParamType.INPUT)
    private java.lang.String operationImplementationRef;

    public java.lang.String getOperationImplementationRef() {
        return operationImplementationRef;
    }

    public void setOperationImplementationRef(java.lang.String operationImplementationRef) {
        this.operationImplementationRef = operationImplementationRef;
    }

    @UserTaskParam(value = ParamType.INPUT)
    private java.lang.String implementation;

    public java.lang.String getImplementation() {
        return implementation;
    }

    public void setImplementation(java.lang.String implementation) {
        this.implementation = implementation;
    }
}
