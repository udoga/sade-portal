package com.sadesoft.portal.core.filter;

public class EngagementFilter implements Cloneable {
    public String projectId;
    public String employeeId;

    public EngagementFilter() {
    }

    public EngagementFilter copy() {
        try {
            return (EngagementFilter) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public EngagementFilter withEmployeeId(String employeeId) {
        EngagementFilter filter = copy();
        filter.employeeId = employeeId;
        return filter;
    }

    public EngagementFilter withProjectId(String projectId) {
        EngagementFilter filter = copy();
        filter.projectId = projectId;
        return filter;
    }
}