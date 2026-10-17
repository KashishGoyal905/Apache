package org.kie.kogito.handlers;

import org.kie.kogito.internal.process.workitem.WorkItemExecutionException;

@org.springframework.stereotype.Component()
public class AftermarketDomainEvents_finalizeCcrDirect_finalizeRequest_Handler extends org.kie.kogito.process.workitems.impl.DefaultKogitoWorkItemHandler {

    com.aftermarket.backend.workflow.AftermarketDomainEvents service;

    public AftermarketDomainEvents_finalizeCcrDirect_finalizeRequest_Handler() {
        this(new com.aftermarket.backend.workflow.AftermarketDomainEvents());
    }

    @org.springframework.beans.factory.annotation.Autowired()
    public AftermarketDomainEvents_finalizeCcrDirect_finalizeRequest_Handler(com.aftermarket.backend.workflow.AftermarketDomainEvents service) {
        this.service = service;
    }

    public java.util.Optional<org.kie.kogito.internal.process.workitem.WorkItemTransition> activateWorkItemHandler(org.kie.kogito.internal.process.workitem.KogitoWorkItemManager workItemManager, org.kie.kogito.internal.process.workitem.KogitoWorkItemHandler workItemHandler, org.kie.kogito.internal.process.workitem.KogitoWorkItem workItem, org.kie.kogito.internal.process.workitem.WorkItemTransition transition) {
        java.lang.String businessKey = (java.lang.String) workItem.getParameter("businessKey");
        java.lang.Boolean approved = (java.lang.Boolean) workItem.getParameter("approved");
        java.lang.String lastCompletedBy = (java.lang.String) workItem.getParameter("lastCompletedBy");
        org.kie.kogito.internal.process.workitem.WorkItemRecordParameters.recordInputParameters(workItem, "businessKey", "approved", "lastCompletedBy");
        java.util.Map<java.lang.String, java.lang.Object> result;
        service.finalizeCcrDirect(businessKey, approved, lastCompletedBy);
        result = java.util.Collections.emptyMap();
        return java.util.Optional.of(this.workItemLifeCycle.newTransition("complete", workItem.getPhaseStatus(), result));
    }

    public java.util.Optional<org.kie.kogito.internal.process.workitem.WorkItemTransition> abortWorkItemHandler(org.kie.kogito.internal.process.workitem.KogitoWorkItemManager workItemManager, org.kie.kogito.internal.process.workitem.KogitoWorkItemHandler workItemHandler, org.kie.kogito.internal.process.workitem.KogitoWorkItem workItem, org.kie.kogito.internal.process.workitem.WorkItemTransition transition) {
        return java.util.Optional.empty();
    }

    public String getName() {
        return "com.aftermarket.backend.workflow.AftermarketDomainEvents_finalizeCcrDirect_finalizeRequest_Handler";
    }
}
