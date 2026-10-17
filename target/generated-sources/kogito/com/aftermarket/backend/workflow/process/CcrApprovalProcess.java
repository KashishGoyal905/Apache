package com.aftermarket.backend.workflow.process;

import com.aftermarket.backend.workflow.process.CcrApprovalModel;
import org.kie.api.definition.process.Process;
import org.jbpm.ruleflow.core.RuleFlowProcessFactory;
import org.jbpm.process.core.datatype.impl.type.ObjectDataType;
import org.drools.core.util.KieFunctions;
import com.aftermarket.backend.workflow.AftermarketDomainEvents;
import org.jbpm.process.core.datatype.impl.type.StringDataType;
import org.jbpm.process.core.datatype.impl.type.BooleanDataType;

@org.springframework.stereotype.Component("ccrApproval")
public class CcrApprovalProcess extends org.kie.kogito.process.impl.AbstractProcess<com.aftermarket.backend.workflow.process.CcrApprovalModel> {

    public CcrApprovalProcess(org.kie.kogito.app.Application app, org.kie.kogito.correlation.CorrelationService correlations) {
        this(app, correlations, new org.kie.kogito.handlers.AftermarketDomainEvents_notifyManager_notifyManager_Handler(), new org.kie.kogito.handlers.AftermarketDomainEvents_notifyFinance_notifyFinance_Handler(), new org.kie.kogito.handlers.AftermarketDomainEvents_remindManager_remindManager_Handler(), new org.kie.kogito.handlers.AftermarketDomainEvents_recordSubmission_recordSubmission_Handler(), new org.kie.kogito.handlers.AftermarketDomainEvents_escalateManager_escalateManager_Handler(), new org.kie.kogito.handlers.AftermarketDomainEvents_finalizeCcrDirect_finalizeRequest_Handler(), new org.kie.kogito.handlers.AftermarketDomainEvents_notifyOutcome_notifyOutcome_Handler());
    }

    @org.springframework.beans.factory.annotation.Autowired()
    public CcrApprovalProcess(org.kie.kogito.app.Application app, org.kie.kogito.correlation.CorrelationService correlations, org.kie.kogito.handlers.AftermarketDomainEvents_notifyManager_notifyManager_Handler aftermarketDomainEvents_notifyManager_notifyManager_Handler, org.kie.kogito.handlers.AftermarketDomainEvents_notifyFinance_notifyFinance_Handler aftermarketDomainEvents_notifyFinance_notifyFinance_Handler, org.kie.kogito.handlers.AftermarketDomainEvents_remindManager_remindManager_Handler aftermarketDomainEvents_remindManager_remindManager_Handler, org.kie.kogito.handlers.AftermarketDomainEvents_recordSubmission_recordSubmission_Handler aftermarketDomainEvents_recordSubmission_recordSubmission_Handler, org.kie.kogito.handlers.AftermarketDomainEvents_escalateManager_escalateManager_Handler aftermarketDomainEvents_escalateManager_escalateManager_Handler, org.kie.kogito.handlers.AftermarketDomainEvents_finalizeCcrDirect_finalizeRequest_Handler aftermarketDomainEvents_finalizeCcrDirect_finalizeRequest_Handler, org.kie.kogito.handlers.AftermarketDomainEvents_notifyOutcome_notifyOutcome_Handler aftermarketDomainEvents_notifyOutcome_notifyOutcome_Handler) {
        super(app, java.util.Arrays.asList(aftermarketDomainEvents_notifyManager_notifyManager_Handler, aftermarketDomainEvents_notifyFinance_notifyFinance_Handler, aftermarketDomainEvents_remindManager_remindManager_Handler, aftermarketDomainEvents_recordSubmission_recordSubmission_Handler, aftermarketDomainEvents_escalateManager_escalateManager_Handler, aftermarketDomainEvents_finalizeCcrDirect_finalizeRequest_Handler, aftermarketDomainEvents_notifyOutcome_notifyOutcome_Handler), correlations);
        activate();
    }

    public CcrApprovalProcess() {
    }

    @Override()
    public com.aftermarket.backend.workflow.process.CcrApprovalProcessInstance createInstance(com.aftermarket.backend.workflow.process.CcrApprovalModel value) {
        return new com.aftermarket.backend.workflow.process.CcrApprovalProcessInstance(this, value, this.createProcessRuntime());
    }

    public com.aftermarket.backend.workflow.process.CcrApprovalProcessInstance createInstance(java.lang.String businessKey, com.aftermarket.backend.workflow.process.CcrApprovalModel value) {
        return new com.aftermarket.backend.workflow.process.CcrApprovalProcessInstance(this, value, businessKey, this.createProcessRuntime());
    }

    public com.aftermarket.backend.workflow.process.CcrApprovalProcessInstance createInstance(java.lang.String businessKey, org.kie.kogito.correlation.CompositeCorrelation correlation, com.aftermarket.backend.workflow.process.CcrApprovalModel value) {
        return new com.aftermarket.backend.workflow.process.CcrApprovalProcessInstance(this, value, businessKey, this.createProcessRuntime(), correlation);
    }

    @Override()
    public com.aftermarket.backend.workflow.process.CcrApprovalModel createModel() {
        return new com.aftermarket.backend.workflow.process.CcrApprovalModel();
    }

    public com.aftermarket.backend.workflow.process.CcrApprovalProcessInstance createInstance(org.kie.kogito.Model value) {
        return this.createInstance((com.aftermarket.backend.workflow.process.CcrApprovalModel) value);
    }

    public com.aftermarket.backend.workflow.process.CcrApprovalProcessInstance createInstance(java.lang.String businessKey, org.kie.kogito.Model value) {
        return this.createInstance(businessKey, (com.aftermarket.backend.workflow.process.CcrApprovalModel) value);
    }

    public com.aftermarket.backend.workflow.process.CcrApprovalProcessInstance createInstance(org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        return new com.aftermarket.backend.workflow.process.CcrApprovalProcessInstance(this, this.createModel(), this.createProcessRuntime(), wpi);
    }

    public com.aftermarket.backend.workflow.process.CcrApprovalProcessInstance createReadOnlyInstance(org.kie.api.runtime.process.WorkflowProcessInstance wpi) {
        return new com.aftermarket.backend.workflow.process.CcrApprovalProcessInstance(this, this.createModel(), wpi);
    }

    protected org.kie.api.definition.process.Process process() {
        RuleFlowProcessFactory factory = RuleFlowProcessFactory.createProcess("ccrApproval", true);
        factory.variable("requestId", org.jbpm.process.core.datatype.DataTypeResolver.fromClass(java.lang.String.class), java.util.Map.of("requestId", "requestId", "ItemSubjectRef", "_requestIdItem"));
        factory.variable("entityType", org.jbpm.process.core.datatype.DataTypeResolver.fromClass(java.lang.String.class), java.util.Map.of("entityType", "entityType", "ItemSubjectRef", "_entityTypeItem"));
        factory.variable("submittedBy", org.jbpm.process.core.datatype.DataTypeResolver.fromClass(java.lang.String.class), java.util.Map.of("submittedBy", "submittedBy", "ItemSubjectRef", "_submittedByItem"));
        factory.variable("employeeGroup", org.jbpm.process.core.datatype.DataTypeResolver.fromClass(java.lang.String.class), java.util.Map.of("employeeGroup", "employeeGroup", "ItemSubjectRef", "_employeeGroupItem"));
        factory.variable("supervisorGroup", org.jbpm.process.core.datatype.DataTypeResolver.fromClass(java.lang.String.class), java.util.Map.of("supervisorGroup", "supervisorGroup", "ItemSubjectRef", "_supervisorGroupItem"));
        factory.variable("financeGroup", org.jbpm.process.core.datatype.DataTypeResolver.fromClass(java.lang.String.class), java.util.Map.of("financeGroup", "financeGroup", "ItemSubjectRef", "_financeGroupItem"));
        factory.variable("approved", org.jbpm.process.core.datatype.DataTypeResolver.fromClass(java.lang.Boolean.class), java.util.Map.of("approved", "approved", "ItemSubjectRef", "_approvedItem"));
        factory.variable("decision", org.jbpm.process.core.datatype.DataTypeResolver.fromClass(java.lang.String.class), java.util.Map.of("decision", "decision", "ItemSubjectRef", "_decisionItem"));
        factory.variable("comment", org.jbpm.process.core.datatype.DataTypeResolver.fromClass(java.lang.String.class), java.util.Map.of("comment", "comment", "ItemSubjectRef", "_commentItem"));
        factory.variable("lastCompletedBy", org.jbpm.process.core.datatype.DataTypeResolver.fromClass(java.lang.String.class), java.util.Map.of("lastCompletedBy", "lastCompletedBy", "ItemSubjectRef", "_lastCompletedByItem"));
        factory.variable("managerReminderDuration", org.jbpm.process.core.datatype.DataTypeResolver.fromClass(java.lang.String.class), java.util.Map.of("managerReminderDuration", "managerReminderDuration", "ItemSubjectRef", "_managerReminderDurationItem"));
        factory.variable("managerEscalationDuration", org.jbpm.process.core.datatype.DataTypeResolver.fromClass(java.lang.String.class), java.util.Map.of("managerEscalationDuration", "managerEscalationDuration", "ItemSubjectRef", "_managerEscalationDurationItem"));
        factory.name("CCR Approval");
        factory.packageName("com.aftermarket.backend.workflow.process");
        factory.dynamic(false);
        factory.version("1.0");
        factory.type("BPMN");
        factory.visibility("Public");
        factory.metaData("jbpm.enable.multi.con", null);
        factory.metaData("jbpm.transactions.enable", "true");
        factory.metaData("TargetNamespace", "http://www.omg.org/bpmn20");
        factory.imports("com.aftermarket.backend.workflow.AftermarketDomainEvents");
        org.jbpm.ruleflow.core.factory.StartNodeFactory<?> startNodestart = factory.startNode(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("start"));
        startNodestart.name("CCR submitted");
        startNodestart.interrupting(false);
        startNodestart.metaData("UniqueId", "start");
        startNodestart.metaData("EventType", "none");
        startNodestart.done();
        org.jbpm.ruleflow.core.factory.WorkItemNodeFactory<?> workItemNoderecordSubmission = factory.workItemNode(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("recordSubmission"));
        workItemNoderecordSubmission.name("Record submission");
        workItemNoderecordSubmission.workName("com.aftermarket.backend.workflow.AftermarketDomainEvents_recordSubmission_recordSubmission_Handler");
        workItemNoderecordSubmission.workParameter("Interface", "com.aftermarket.backend.workflow.AftermarketDomainEvents");
        workItemNoderecordSubmission.workParameter("NodeName", "Record submission");
        workItemNoderecordSubmission.workParameter("Operation", "recordSubmission");
        workItemNoderecordSubmission.workParameter("UNIQUE_TASK_ID", "recordSubmission");
        workItemNoderecordSubmission.workParameter("implementation", "Java");
        workItemNoderecordSubmission.workParameter("interfaceImplementationRef", "com.aftermarket.backend.workflow.AftermarketDomainEvents");
        workItemNoderecordSubmission.workParameter("operationImplementationRef", "recordSubmission");
        workItemNoderecordSubmission.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("requestId", "requestId", "java.lang.String", null)), new org.jbpm.workflow.core.impl.DataDefinition("_recordSubmission_businessKeyInputX", "businessKey", "String", null), null, null));
        workItemNoderecordSubmission.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("submittedBy", "submittedBy", "java.lang.String", null)), new org.jbpm.workflow.core.impl.DataDefinition("_recordSubmission_submittedByInputX", "submittedBy", "String", null), null, null));
        workItemNoderecordSubmission.done();
        workItemNoderecordSubmission.metaData("UniqueId", "recordSubmission");
        workItemNoderecordSubmission.metaData("elementname", "Record submission");
        workItemNoderecordSubmission.metaData("Type", "Service Task");
        workItemNoderecordSubmission.metaData("OperationRef", "_recordSubmission_ServiceOperation");
        workItemNoderecordSubmission.metaData("Implementation", "Java");
        org.jbpm.ruleflow.core.factory.WorkItemNodeFactory<?> workItemNodenotifyManager = factory.workItemNode(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("notifyManager"));
        workItemNodenotifyManager.name("Notify manager");
        workItemNodenotifyManager.workName("com.aftermarket.backend.workflow.AftermarketDomainEvents_notifyManager_notifyManager_Handler");
        workItemNodenotifyManager.workParameter("Interface", "com.aftermarket.backend.workflow.AftermarketDomainEvents");
        workItemNodenotifyManager.workParameter("NodeName", "Notify manager");
        workItemNodenotifyManager.workParameter("Operation", "notifyManager");
        workItemNodenotifyManager.workParameter("UNIQUE_TASK_ID", "notifyManager");
        workItemNodenotifyManager.workParameter("implementation", "Java");
        workItemNodenotifyManager.workParameter("interfaceImplementationRef", "com.aftermarket.backend.workflow.AftermarketDomainEvents");
        workItemNodenotifyManager.workParameter("operationImplementationRef", "notifyManager");
        workItemNodenotifyManager.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("requestId", "requestId", "java.lang.String", null)), new org.jbpm.workflow.core.impl.DataDefinition("_notifyManager_businessKeyInputX", "businessKey", "String", null), null, null));
        workItemNodenotifyManager.done();
        workItemNodenotifyManager.metaData("UniqueId", "notifyManager");
        workItemNodenotifyManager.metaData("elementname", "Notify manager");
        workItemNodenotifyManager.metaData("Type", "Service Task");
        workItemNodenotifyManager.metaData("OperationRef", "_notifyManager_ServiceOperation");
        workItemNodenotifyManager.metaData("Implementation", "Java");
        org.jbpm.ruleflow.core.factory.HumanTaskNodeFactory<?> humanTaskNodemanagerApproval = factory.humanTaskNode(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("managerApproval"));
        humanTaskNodemanagerApproval.name("Manager approval");
        humanTaskNodemanagerApproval.workParameter("NodeName", "Manager approval");
        humanTaskNodemanagerApproval.workParameter("TaskName", "manager approval");
        humanTaskNodemanagerApproval.workParameter("UNIQUE_TASK_ID", "managerApproval");
        humanTaskNodemanagerApproval.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(), new org.jbpm.workflow.core.impl.DataDefinition("_managerApproval_TaskNameInputX", "TaskName", "String", null), java.util.Arrays.asList(new org.jbpm.workflow.core.node.Assignment(null, new org.jbpm.workflow.core.impl.DataDefinition("", "EXPRESSION (manager approval)", "java.lang.Object", "manager approval"), new org.jbpm.workflow.core.impl.DataDefinition("_managerApproval_TaskNameInputX", "TaskName", "String", null))), null));
        humanTaskNodemanagerApproval.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(), new org.jbpm.workflow.core.impl.DataDefinition("_managerApproval_taskKeyInputX", "taskKey", "String", null), java.util.Arrays.asList(new org.jbpm.workflow.core.node.Assignment(null, new org.jbpm.workflow.core.impl.DataDefinition("", "EXPRESSION (managerApproval)", "java.lang.Object", "managerApproval"), new org.jbpm.workflow.core.impl.DataDefinition("_managerApproval_taskKeyInputX", "taskKey", "String", null))), null));
        humanTaskNodemanagerApproval.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("supervisorGroup", "supervisorGroup", "java.lang.String", null)), new org.jbpm.workflow.core.impl.DataDefinition("_managerApproval_GroupIdInputX", "GroupId", "String", null), null, null));
        humanTaskNodemanagerApproval.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("requestId", "requestId", "java.lang.String", null)), new org.jbpm.workflow.core.impl.DataDefinition("_managerApproval_requestIdInputX", "requestId", "String", null), null, null));
        humanTaskNodemanagerApproval.mapDataOutputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("_managerApproval_approvedOutputX", "approved", "Boolean", null)), new org.jbpm.workflow.core.impl.DataDefinition("approved", "approved", "java.lang.Boolean", null), null, null));
        humanTaskNodemanagerApproval.mapDataOutputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("_managerApproval_decisionOutputX", "decision", "String", null)), new org.jbpm.workflow.core.impl.DataDefinition("decision", "decision", "java.lang.String", null), null, null));
        humanTaskNodemanagerApproval.mapDataOutputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("_managerApproval_commentOutputX", "comment", "String", null)), new org.jbpm.workflow.core.impl.DataDefinition("comment", "comment", "java.lang.String", null), null, null));
        humanTaskNodemanagerApproval.mapDataOutputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("_managerApproval_lastCompletedByOutputX", "lastCompletedBy", "String", null)), new org.jbpm.workflow.core.impl.DataDefinition("lastCompletedBy", "lastCompletedBy", "java.lang.String", null), null, null));
        humanTaskNodemanagerApproval.done();
        humanTaskNodemanagerApproval.metaData("UniqueId", "managerApproval");
        humanTaskNodemanagerApproval.metaData("elementname", "Manager approval");
        org.jbpm.ruleflow.core.factory.BoundaryEventNodeFactory<?> boundaryEventNodemanagerReminderTimer = factory.boundaryEventNode(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("managerReminderTimer"));
        boundaryEventNodemanagerReminderTimer.metaData("UniqueId", "managerReminderTimer");
        boundaryEventNodemanagerReminderTimer.metaData("EventType", "timer");
        boundaryEventNodemanagerReminderTimer.metaData("AttachedTo", "managerApproval");
        boundaryEventNodemanagerReminderTimer.metaData("TimeDuration", "#{managerReminderDuration}");
        boundaryEventNodemanagerReminderTimer.metaData("CancelActivity", false);
        boundaryEventNodemanagerReminderTimer.name("Reminder");
        boundaryEventNodemanagerReminderTimer.eventType("Timer-managerApproval-#{managerReminderDuration}-managerReminderTimer");
        boundaryEventNodemanagerReminderTimer.attachedTo("managerApproval");
        boundaryEventNodemanagerReminderTimer.scope(null);
        boundaryEventNodemanagerReminderTimer.done();
        org.jbpm.ruleflow.core.factory.BoundaryEventNodeFactory<?> boundaryEventNodemanagerEscalationTimer = factory.boundaryEventNode(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("managerEscalationTimer"));
        boundaryEventNodemanagerEscalationTimer.metaData("UniqueId", "managerEscalationTimer");
        boundaryEventNodemanagerEscalationTimer.metaData("EventType", "timer");
        boundaryEventNodemanagerEscalationTimer.metaData("AttachedTo", "managerApproval");
        boundaryEventNodemanagerEscalationTimer.metaData("TimeDuration", "#{managerEscalationDuration}");
        boundaryEventNodemanagerEscalationTimer.metaData("CancelActivity", true);
        boundaryEventNodemanagerEscalationTimer.name("Escalation");
        boundaryEventNodemanagerEscalationTimer.eventType("Timer-managerApproval-#{managerEscalationDuration}-managerEscalationTimer");
        boundaryEventNodemanagerEscalationTimer.attachedTo("managerApproval");
        boundaryEventNodemanagerEscalationTimer.scope(null);
        boundaryEventNodemanagerEscalationTimer.done();
        org.jbpm.ruleflow.core.factory.WorkItemNodeFactory<?> workItemNoderemindManager = factory.workItemNode(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("remindManager"));
        workItemNoderemindManager.name("Send reminder");
        workItemNoderemindManager.workName("com.aftermarket.backend.workflow.AftermarketDomainEvents_remindManager_remindManager_Handler");
        workItemNoderemindManager.workParameter("Interface", "com.aftermarket.backend.workflow.AftermarketDomainEvents");
        workItemNoderemindManager.workParameter("NodeName", "Send reminder");
        workItemNoderemindManager.workParameter("Operation", "remindManager");
        workItemNoderemindManager.workParameter("UNIQUE_TASK_ID", "remindManager");
        workItemNoderemindManager.workParameter("implementation", "Java");
        workItemNoderemindManager.workParameter("interfaceImplementationRef", "com.aftermarket.backend.workflow.AftermarketDomainEvents");
        workItemNoderemindManager.workParameter("operationImplementationRef", "remindManager");
        workItemNoderemindManager.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("requestId", "requestId", "java.lang.String", null)), new org.jbpm.workflow.core.impl.DataDefinition("_remindManager_businessKeyInputX", "businessKey", "String", null), null, null));
        workItemNoderemindManager.done();
        workItemNoderemindManager.metaData("UniqueId", "remindManager");
        workItemNoderemindManager.metaData("elementname", "Send reminder");
        workItemNoderemindManager.metaData("Type", "Service Task");
        workItemNoderemindManager.metaData("OperationRef", "_remindManager_ServiceOperation");
        workItemNoderemindManager.metaData("Implementation", "Java");
        org.jbpm.ruleflow.core.factory.WorkItemNodeFactory<?> workItemNodeescalateManager = factory.workItemNode(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("escalateManager"));
        workItemNodeescalateManager.name("Escalate to management");
        workItemNodeescalateManager.workName("com.aftermarket.backend.workflow.AftermarketDomainEvents_escalateManager_escalateManager_Handler");
        workItemNodeescalateManager.workParameter("Interface", "com.aftermarket.backend.workflow.AftermarketDomainEvents");
        workItemNodeescalateManager.workParameter("NodeName", "Escalate to management");
        workItemNodeescalateManager.workParameter("Operation", "escalateManager");
        workItemNodeescalateManager.workParameter("UNIQUE_TASK_ID", "escalateManager");
        workItemNodeescalateManager.workParameter("implementation", "Java");
        workItemNodeescalateManager.workParameter("interfaceImplementationRef", "com.aftermarket.backend.workflow.AftermarketDomainEvents");
        workItemNodeescalateManager.workParameter("operationImplementationRef", "escalateManager");
        workItemNodeescalateManager.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("requestId", "requestId", "java.lang.String", null)), new org.jbpm.workflow.core.impl.DataDefinition("_escalateManager_businessKeyInputX", "businessKey", "String", null), null, null));
        workItemNodeescalateManager.done();
        workItemNodeescalateManager.metaData("UniqueId", "escalateManager");
        workItemNodeescalateManager.metaData("elementname", "Escalate to management");
        workItemNodeescalateManager.metaData("Type", "Service Task");
        workItemNodeescalateManager.metaData("OperationRef", "_escalateManager_ServiceOperation");
        workItemNodeescalateManager.metaData("Implementation", "Java");
        org.jbpm.ruleflow.core.factory.EndNodeFactory<?> endNodereminderSent = factory.endNode(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("reminderSent"));
        endNodereminderSent.name("Reminder sent");
        endNodereminderSent.terminate(false);
        endNodereminderSent.metaData("UniqueId", "reminderSent");
        endNodereminderSent.done();
        org.jbpm.ruleflow.core.factory.SplitFactory<?> splitNodemanagerDecisionGateway = factory.splitNode(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("managerDecisionGateway"));
        splitNodemanagerDecisionGateway.name("Manager decision");
        splitNodemanagerDecisionGateway.type(2);
        splitNodemanagerDecisionGateway.metaData("UniqueId", "managerDecisionGateway");
        splitNodemanagerDecisionGateway.metaData("Default", "flow-manager-not-approved");
        splitNodemanagerDecisionGateway.constraint(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("notifyFinance"), "flow-manager-approved", "DROOLS_DEFAULT", "java", kcontext -> {
            java.lang.Boolean approved = (java.lang.Boolean) kcontext.getVariable("approved");
            return approved != null && approved;
        }, 0, false);
        splitNodemanagerDecisionGateway.done();
        splitNodemanagerDecisionGateway.constraint(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("notifyFinance"), "flow-manager-approved", "DROOLS_DEFAULT", "java", kcontext -> {
            java.lang.Boolean approved = (java.lang.Boolean) kcontext.getVariable("approved");
            return approved != null && approved;
        }, 0, false);
        org.jbpm.ruleflow.core.factory.WorkItemNodeFactory<?> workItemNodenotifyFinance = factory.workItemNode(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("notifyFinance"));
        workItemNodenotifyFinance.name("Notify finance");
        workItemNodenotifyFinance.workName("com.aftermarket.backend.workflow.AftermarketDomainEvents_notifyFinance_notifyFinance_Handler");
        workItemNodenotifyFinance.workParameter("Interface", "com.aftermarket.backend.workflow.AftermarketDomainEvents");
        workItemNodenotifyFinance.workParameter("NodeName", "Notify finance");
        workItemNodenotifyFinance.workParameter("Operation", "notifyFinance");
        workItemNodenotifyFinance.workParameter("UNIQUE_TASK_ID", "notifyFinance");
        workItemNodenotifyFinance.workParameter("implementation", "Java");
        workItemNodenotifyFinance.workParameter("interfaceImplementationRef", "com.aftermarket.backend.workflow.AftermarketDomainEvents");
        workItemNodenotifyFinance.workParameter("operationImplementationRef", "notifyFinance");
        workItemNodenotifyFinance.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("requestId", "requestId", "java.lang.String", null)), new org.jbpm.workflow.core.impl.DataDefinition("_notifyFinance_businessKeyInputX", "businessKey", "String", null), null, null));
        workItemNodenotifyFinance.done();
        workItemNodenotifyFinance.metaData("UniqueId", "notifyFinance");
        workItemNodenotifyFinance.metaData("elementname", "Notify finance");
        workItemNodenotifyFinance.metaData("Type", "Service Task");
        workItemNodenotifyFinance.metaData("OperationRef", "_notifyFinance_ServiceOperation");
        workItemNodenotifyFinance.metaData("Implementation", "Java");
        org.jbpm.ruleflow.core.factory.HumanTaskNodeFactory<?> humanTaskNodefinanceApproval = factory.humanTaskNode(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("financeApproval"));
        humanTaskNodefinanceApproval.name("Finance approval");
        humanTaskNodefinanceApproval.workParameter("NodeName", "Finance approval");
        humanTaskNodefinanceApproval.workParameter("TaskName", "finance approval");
        humanTaskNodefinanceApproval.workParameter("UNIQUE_TASK_ID", "financeApproval");
        humanTaskNodefinanceApproval.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(), new org.jbpm.workflow.core.impl.DataDefinition("_financeApproval_TaskNameInputX", "TaskName", "String", null), java.util.Arrays.asList(new org.jbpm.workflow.core.node.Assignment(null, new org.jbpm.workflow.core.impl.DataDefinition("", "EXPRESSION (finance approval)", "java.lang.Object", "finance approval"), new org.jbpm.workflow.core.impl.DataDefinition("_financeApproval_TaskNameInputX", "TaskName", "String", null))), null));
        humanTaskNodefinanceApproval.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(), new org.jbpm.workflow.core.impl.DataDefinition("_financeApproval_taskKeyInputX", "taskKey", "String", null), java.util.Arrays.asList(new org.jbpm.workflow.core.node.Assignment(null, new org.jbpm.workflow.core.impl.DataDefinition("", "EXPRESSION (financeApproval)", "java.lang.Object", "financeApproval"), new org.jbpm.workflow.core.impl.DataDefinition("_financeApproval_taskKeyInputX", "taskKey", "String", null))), null));
        humanTaskNodefinanceApproval.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("financeGroup", "financeGroup", "java.lang.String", null)), new org.jbpm.workflow.core.impl.DataDefinition("_financeApproval_GroupIdInputX", "GroupId", "String", null), null, null));
        humanTaskNodefinanceApproval.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("requestId", "requestId", "java.lang.String", null)), new org.jbpm.workflow.core.impl.DataDefinition("_financeApproval_requestIdInputX", "requestId", "String", null), null, null));
        humanTaskNodefinanceApproval.mapDataOutputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("_financeApproval_approvedOutputX", "approved", "Boolean", null)), new org.jbpm.workflow.core.impl.DataDefinition("approved", "approved", "java.lang.Boolean", null), null, null));
        humanTaskNodefinanceApproval.mapDataOutputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("_financeApproval_decisionOutputX", "decision", "String", null)), new org.jbpm.workflow.core.impl.DataDefinition("decision", "decision", "java.lang.String", null), null, null));
        humanTaskNodefinanceApproval.mapDataOutputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("_financeApproval_commentOutputX", "comment", "String", null)), new org.jbpm.workflow.core.impl.DataDefinition("comment", "comment", "java.lang.String", null), null, null));
        humanTaskNodefinanceApproval.mapDataOutputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("_financeApproval_lastCompletedByOutputX", "lastCompletedBy", "String", null)), new org.jbpm.workflow.core.impl.DataDefinition("lastCompletedBy", "lastCompletedBy", "java.lang.String", null), null, null));
        humanTaskNodefinanceApproval.done();
        humanTaskNodefinanceApproval.metaData("UniqueId", "financeApproval");
        humanTaskNodefinanceApproval.metaData("elementname", "Finance approval");
        org.jbpm.ruleflow.core.factory.JoinFactory<?> joinNodejoinGateway = factory.joinNode(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("joinGateway"));
        joinNodejoinGateway.name("Join to finalize");
        joinNodejoinGateway.type(2);
        joinNodejoinGateway.metaData("UniqueId", "joinGateway");
        joinNodejoinGateway.done();
        org.jbpm.ruleflow.core.factory.WorkItemNodeFactory<?> workItemNodefinalizeRequest = factory.workItemNode(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("finalizeRequest"));
        workItemNodefinalizeRequest.name("Finalize CCR");
        workItemNodefinalizeRequest.workName("com.aftermarket.backend.workflow.AftermarketDomainEvents_finalizeCcrDirect_finalizeRequest_Handler");
        workItemNodefinalizeRequest.workParameter("Interface", "com.aftermarket.backend.workflow.AftermarketDomainEvents");
        workItemNodefinalizeRequest.workParameter("NodeName", "Finalize CCR");
        workItemNodefinalizeRequest.workParameter("Operation", "finalizeCcrDirect");
        workItemNodefinalizeRequest.workParameter("UNIQUE_TASK_ID", "finalizeRequest");
        workItemNodefinalizeRequest.workParameter("implementation", "Java");
        workItemNodefinalizeRequest.workParameter("interfaceImplementationRef", "com.aftermarket.backend.workflow.AftermarketDomainEvents");
        workItemNodefinalizeRequest.workParameter("operationImplementationRef", "finalizeCcrDirect");
        workItemNodefinalizeRequest.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("requestId", "requestId", "java.lang.String", null)), new org.jbpm.workflow.core.impl.DataDefinition("_finalizeRequest_businessKeyInputX", "businessKey", "String", null), null, null));
        workItemNodefinalizeRequest.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("approved", "approved", "java.lang.Boolean", null)), new org.jbpm.workflow.core.impl.DataDefinition("_finalizeRequest_approvedInputX", "approved", "Boolean", null), null, null));
        workItemNodefinalizeRequest.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("lastCompletedBy", "lastCompletedBy", "java.lang.String", null)), new org.jbpm.workflow.core.impl.DataDefinition("_finalizeRequest_lastCompletedByInputX", "lastCompletedBy", "String", null), null, null));
        workItemNodefinalizeRequest.done();
        workItemNodefinalizeRequest.metaData("UniqueId", "finalizeRequest");
        workItemNodefinalizeRequest.metaData("elementname", "Finalize CCR");
        workItemNodefinalizeRequest.metaData("Type", "Service Task");
        workItemNodefinalizeRequest.metaData("OperationRef", "_finalizeRequest_ServiceOperation");
        workItemNodefinalizeRequest.metaData("Implementation", "Java");
        org.jbpm.ruleflow.core.factory.SplitFactory<?> splitNodeoutcomeGateway = factory.splitNode(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("outcomeGateway"));
        splitNodeoutcomeGateway.name("Outcome");
        splitNodeoutcomeGateway.type(2);
        splitNodeoutcomeGateway.metaData("UniqueId", "outcomeGateway");
        splitNodeoutcomeGateway.metaData("Default", "flow-outcome-rejected");
        splitNodeoutcomeGateway.constraint(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("notifyOutcome"), "flow-outcome-approved", "DROOLS_DEFAULT", "java", kcontext -> {
            java.lang.Boolean approved = (java.lang.Boolean) kcontext.getVariable("approved");
            return approved != null && approved;
        }, 0, false);
        splitNodeoutcomeGateway.done();
        splitNodeoutcomeGateway.constraint(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("notifyOutcome"), "flow-outcome-approved", "DROOLS_DEFAULT", "java", kcontext -> {
            java.lang.Boolean approved = (java.lang.Boolean) kcontext.getVariable("approved");
            return approved != null && approved;
        }, 0, false);
        org.jbpm.ruleflow.core.factory.WorkItemNodeFactory<?> workItemNodenotifyOutcome = factory.workItemNode(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("notifyOutcome"));
        workItemNodenotifyOutcome.name("Notify approval");
        workItemNodenotifyOutcome.workName("com.aftermarket.backend.workflow.AftermarketDomainEvents_notifyOutcome_notifyOutcome_Handler");
        workItemNodenotifyOutcome.workParameter("Interface", "com.aftermarket.backend.workflow.AftermarketDomainEvents");
        workItemNodenotifyOutcome.workParameter("NodeName", "Notify approval");
        workItemNodenotifyOutcome.workParameter("Operation", "notifyOutcome");
        workItemNodenotifyOutcome.workParameter("UNIQUE_TASK_ID", "notifyOutcome");
        workItemNodenotifyOutcome.workParameter("implementation", "Java");
        workItemNodenotifyOutcome.workParameter("interfaceImplementationRef", "com.aftermarket.backend.workflow.AftermarketDomainEvents");
        workItemNodenotifyOutcome.workParameter("operationImplementationRef", "notifyOutcome");
        workItemNodenotifyOutcome.mapDataInputAssociation(new org.jbpm.workflow.core.impl.DataAssociation(java.util.Arrays.asList(new org.jbpm.workflow.core.impl.DataDefinition("requestId", "requestId", "java.lang.String", null)), new org.jbpm.workflow.core.impl.DataDefinition("_notifyOutcome_businessKeyInputX", "businessKey", "String", null), null, null));
        workItemNodenotifyOutcome.done();
        workItemNodenotifyOutcome.metaData("UniqueId", "notifyOutcome");
        workItemNodenotifyOutcome.metaData("elementname", "Notify approval");
        workItemNodenotifyOutcome.metaData("Type", "Service Task");
        workItemNodenotifyOutcome.metaData("OperationRef", "_notifyOutcome_ServiceOperation");
        workItemNodenotifyOutcome.metaData("Implementation", "Java");
        org.jbpm.ruleflow.core.factory.EndNodeFactory<?> endNodecompleted = factory.endNode(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("completed"));
        endNodecompleted.name("Approved");
        endNodecompleted.terminate(false);
        endNodecompleted.metaData("UniqueId", "completed");
        endNodecompleted.done();
        org.jbpm.ruleflow.core.factory.EndNodeFactory<?> endNoderejected = factory.endNode(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("rejected"));
        endNoderejected.name("Rejected");
        endNoderejected.terminate(false);
        endNoderejected.metaData("UniqueId", "rejected");
        endNoderejected.done();
        factory.connection(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("start"), org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("recordSubmission"), "flow-start");
        factory.connection(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("recordSubmission"), org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("notifyManager"), "flow-recorded");
        factory.connection(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("notifyManager"), org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("managerApproval"), "flow-notified-manager");
        factory.connection(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("managerReminderTimer"), org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("remindManager"), "flow-reminder");
        factory.connection(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("managerEscalationTimer"), org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("escalateManager"), "flow-escalation");
        factory.connection(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("remindManager"), org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("reminderSent"), "flow-reminder-end");
        factory.connection(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("managerApproval"), org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("managerDecisionGateway"), "flow-manager-decided");
        factory.connection(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("managerDecisionGateway"), org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("notifyFinance"), "flow-manager-approved");
        factory.connection(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("notifyFinance"), org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("financeApproval"), "flow-finance-notified");
        factory.connection(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("managerDecisionGateway"), org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("joinGateway"), "flow-manager-not-approved");
        factory.connection(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("financeApproval"), org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("joinGateway"), "flow-finance-decided");
        factory.connection(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("escalateManager"), org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("joinGateway"), "flow-escalated");
        factory.connection(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("joinGateway"), org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("finalizeRequest"), "flow-to-finalize");
        factory.connection(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("finalizeRequest"), org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("outcomeGateway"), "flow-finalized");
        factory.connection(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("outcomeGateway"), org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("notifyOutcome"), "flow-outcome-approved");
        factory.connection(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("notifyOutcome"), org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("completed"), "flow-approved-end");
        factory.connection(org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("outcomeGateway"), org.jbpm.ruleflow.core.WorkflowElementIdentifierFactory.fromExternalFormat("rejected"), "flow-outcome-rejected");
        factory.validate();
        return factory.getProcess();
    }
}
