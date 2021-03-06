package com.distributedworkflowengine.stateinit.domain;

import org.springframework.stereotype.Component;

@Component
public class Trigger {
    private String jobId;
    private WorkFlow workFlow;
    public Trigger() {
        super();
    }
    public Trigger(String jobId, WorkFlow workFlow) {
        super();
        this.jobId = jobId;
        this.workFlow = workFlow;
    }
    public String getJobId() {
        return jobId;
    }
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
    public WorkFlow getWorkFlow() {
        return workFlow;
    }
    public void setWorkflow(WorkFlow workFlow) {
        this.workFlow = workFlow;
    }
    
}