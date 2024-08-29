package com.sadesoft.portal.core.query;

public class EngagementQuery implements Cloneable {
    public String projectId;
    public String employeeId;

    public EngagementQuery() {
    }

    public EngagementQuery copy() {
        try {
            return (EngagementQuery) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public EngagementQuery withEmployeeId(String employeeId) {
        EngagementQuery copy = copy();
        copy.employeeId = employeeId;
        return copy;
    }

    public EngagementQuery withProjectId(String projectId) {
        EngagementQuery copy = copy();
        copy.projectId = projectId;
        return copy;
    }
}