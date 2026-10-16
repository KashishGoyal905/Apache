package com.aftermarket.backend.workflow;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.aftermarket.backend.domain.Ccr;
import com.aftermarket.backend.repo.CcrRepository;
import com.aftermarket.backend.service.ActivityService;
import com.aftermarket.backend.service.NotificationService;

/**
 * Called by the Java service tasks of {@code processes/ccrApproval.bpmn2}.
 * <p>
 * The Kogito code generator resolves this class by name and injects it as a Spring bean, so the
 * method names and parameter types here must match the service task definitions in the BPMN
 * (operation name + data inputs, in order). The tasks only emit domain events; the JPA layer owns
 * all business writes.
 */
@Component("domainEvents")
public class AftermarketDomainEvents {

    private static final Logger log = LoggerFactory.getLogger(AftermarketDomainEvents.class);

    private final CcrRepository ccrs;
    private final NotificationService notifications;
    private final ActivityService activity;

    public AftermarketDomainEvents(
            CcrRepository ccrs,
            NotificationService notifications,
            ActivityService activity) {
        this.ccrs = ccrs;
        this.notifications = notifications;
        this.activity = activity;
    }

    public void recordSubmission(String businessKey, String submittedBy) {
        log.info("Domain event CCR_SUBMITTED for CCR {}", businessKey);
        handleCcrEvent(businessKey, "CCR_SUBMITTED");
        activity.record(
                "EVENT_CCR_SUBMITTED",
                "CCR_SUBMITTED",
                "Ccr",
                businessKey,
                submittedBy == null ? "system" : submittedBy,
                "{}");
    }

    public void notifyManager(String businessKey) {
        emit(businessKey, "CCR_MANAGER_APPROVAL_REQUESTED");
    }

    public void remindManager(String businessKey) {
        emit(businessKey, "CCR_MANAGER_REMINDER");
    }

    public void escalateManager(String businessKey) {
        emit(businessKey, "CCR_MANAGER_ESCALATION");
    }

    public void notifyFinance(String businessKey) {
        emit(businessKey, "CCR_FINANCE_APPROVAL_REQUESTED");
    }

    public void notifyOutcome(String businessKey) {
        emit(businessKey, "CCR_FINAL_OUTCOME");
    }

    /**
     * Finalizes the CCR. {@code approved} is null when the manager escalation timer fired, which is
     * treated as a rejection (same as the previous BPMN process).
     */
    public void finalizeCcrDirect(String businessKey, Boolean approved, String lastCompletedBy) {
        boolean isApproved = Boolean.TRUE.equals(approved);
        log.info("Finalize CCR {} with approved={}", businessKey, isApproved);
        ccrs.findByReference(businessKey).ifPresent(ccr -> {
            ccr.setStatus(isApproved ? "Approved" : "Rejected");
            ccr.setRecentUpdate(isApproved ? "CCR Approved" : "CCR Rejected");
            ccr.setWorkflowState("ENDED");
            ccr.setCurrentTaskKey(null);
            ccrs.save(ccr);
            activity.record(
                    isApproved ? "WORKFLOW_APPROVED" : "WORKFLOW_CANCELLED",
                    isApproved ? "CCR approved" : "CCR cancelled",
                    "Ccr",
                    businessKey,
                    lastCompletedBy == null ? "system" : lastCompletedBy,
                    "{}");
            notifications.notify(
                    "REQUEST_" + (isApproved ? "APPROVED" : "CANCELLED"),
                    "Request " + businessKey + (isApproved ? " approved" : " cancelled"),
                    isApproved
                            ? "Request " + businessKey + " was approved by finance."
                            : "Request " + businessKey + " was rejected by finance and has been cancelled.",
                    "Ccr",
                    businessKey,
                    recipients(ccr.getCreatedBy()),
                    List.of());
        });
    }

    private void emit(String businessKey, String event) {
        log.info("Domain event {} for CCR {}", event, businessKey);
        handleCcrEvent(businessKey, event);
        activity.record("EVENT_" + event, event, "Ccr", businessKey, "system", "{}");
    }

    private void handleCcrEvent(String reference, String event) {
        Ccr ccr = ccrs.findByReference(reference).orElse(null);
        if (ccr == null) {
            log.warn("CCR not found for event {}: {}", event, reference);
            return;
        }
        switch (event) {
            case "CCR_SUBMITTED" -> {
                ccr.setStatus("Pending Approval");
                ccr.setRecentUpdate("CCR Submitted");
                ccr.setWorkflowState("RUNNING");
                ccrs.save(ccr);
            }
            case "CCR_MANAGER_APPROVAL_REQUESTED" -> notifications.notify(
                    "APPROVAL_REQUESTED",
                    "Approval needed: " + reference,
                    "CCR " + reference + " is waiting for manager approval.",
                    "Ccr",
                    reference,
                    List.of(),
                    List.of("supervisors"));
            case "CCR_MANAGER_REMINDER" -> notifications.notify(
                    "APPROVAL_REMINDER",
                    "Reminder: " + reference + " is still pending",
                    "CCR " + reference + " has been waiting for manager approval.",
                    "Ccr",
                    reference,
                    List.of(),
                    List.of("supervisors"));
            case "CCR_MANAGER_ESCALATION" -> notifications.notify(
                    "APPROVAL_ESCALATED",
                    "Escalated: " + reference + " breached its SLA",
                    "CCR " + reference + " was escalated because it was not approved in time.",
                    "Ccr",
                    reference,
                    List.of(),
                    List.of("supervisors", "finance"));
            case "CCR_FINANCE_APPROVAL_REQUESTED" -> {
                notifications.notify(
                        "APPROVED_BY_MANAGER",
                        "Request " + reference + " approved by manager",
                        "Request " + reference + " was approved by the manager and is now with finance.",
                        "Ccr",
                        reference,
                        recipients(ccr.getCreatedBy()),
                        List.of());
                notifications.notify(
                        "FINANCE_APPROVAL_REQUESTED",
                        "Finance review needed: " + reference,
                        "Request " + reference + " passed manager approval and needs finance review.",
                        "Ccr",
                        reference,
                        List.of(),
                        List.of("finance"));
            }
            default -> {
                // CCR_FINAL_OUTCOME: nothing extra, finalizeCcrDirect already notified the creator.
            }
        }
    }

    private List<String> recipients(String creatorId) {
        List<String> users = new ArrayList<>();
        if (creatorId != null && !creatorId.isBlank()) {
            users.add(creatorId);
        }
        return users;
    }
}
