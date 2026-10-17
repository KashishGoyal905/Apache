package com.aftermarket.backend.workflow;

import org.springframework.stereotype.Component;

/**
 * Target of the Java service tasks in processes/ccrApproval.bpmn2.
 * Kogito's generated handlers do `new AftermarketDomainEvents()`, so this class needs a
 * no-arg constructor and cannot use constructor injection. It forwards to DomainEventsHandler.
 */
@Component("domainEvents")
public class AftermarketDomainEvents {

    public AftermarketDomainEvents() {
    }

    private DomainEventsHandler delegate() {
        return SpringContext.getBean(DomainEventsHandler.class);
    }

    public void recordSubmission(String businessKey, String submittedBy) {
        delegate().recordSubmission(businessKey, submittedBy);
    }

    public void notifyManager(String businessKey) {
        delegate().notifyManager(businessKey);
    }

    public void remindManager(String businessKey) {
        delegate().remindManager(businessKey);
    }

    public void escalateManager(String businessKey) {
        delegate().escalateManager(businessKey);
    }

    public void notifyFinance(String businessKey) {
        delegate().notifyFinance(businessKey);
    }

    public void notifyOutcome(String businessKey) {
        delegate().notifyOutcome(businessKey);
    }

    public void finalizeCcrDirect(String businessKey, Boolean approved, String lastCompletedBy) {
        delegate().finalizeCcrDirect(businessKey, approved, lastCompletedBy);
    }
}
