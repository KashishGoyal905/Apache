package com.aftermarket.backend.workflow.process;

import com.aftermarket.backend.workflow.process.CcrApprovalModel;

public class CcrApprovalProcessInstance extends org.kie.kogito.process.impl.AbstractProcessInstance<CcrApprovalModel> {

    public CcrApprovalProcessInstance(com.aftermarket.backend.workflow.process.CcrApprovalProcess process, CcrApprovalModel value, org.kie.api.runtime.process.ProcessRuntime processRuntime) {
        super(process, value, processRuntime);
    }

    public CcrApprovalProcessInstance(com.aftermarket.backend.workflow.process.CcrApprovalProcess process, CcrApprovalModel value, java.lang.String businessKey, org.kie.api.runtime.process.ProcessRuntime processRuntime) {
        super(process, value, businessKey, processRuntime);
    }

    public CcrApprovalProcessInstance(com.aftermarket.backend.workflow.process.CcrApprovalProcess process, CcrApprovalModel value, org.kie.api.runtime.process.ProcessRuntime processRuntime, org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        super(process, value, processRuntime, wpi);
    }

    public CcrApprovalProcessInstance(com.aftermarket.backend.workflow.process.CcrApprovalProcess process, CcrApprovalModel value, org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        super(process, value, wpi);
    }

    public CcrApprovalProcessInstance(com.aftermarket.backend.workflow.process.CcrApprovalProcess process, CcrApprovalModel value, java.lang.String businessKey, org.kie.api.runtime.process.ProcessRuntime processRuntime, org.kie.kogito.correlation.CompositeCorrelation correlation) {
        super(process, value, businessKey, processRuntime, correlation);
    }

    protected java.util.Map<String, Object> bind(CcrApprovalModel variables) {
        if (null != variables)
            return variables.toMap();
        else
            return new java.util.HashMap();
    }

    protected void unbind(CcrApprovalModel variables, java.util.Map<String, Object> vmap) {
        variables.fromMap(this.id(), vmap);
    }
}
