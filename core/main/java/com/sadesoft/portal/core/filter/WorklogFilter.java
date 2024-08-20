package com.sadesoft.portal.core.filter;

public class WorklogFilter implements Cloneable {
    public Integer offset;
    public String projectId;
    public String employeeId;

    public WorklogFilter() {
    }

    public WorklogFilter copy() {
        try {
            return (WorklogFilter) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public WorklogFilter withOffset(Integer offset) {
        WorklogFilter filter = copy();
        filter.offset = offset;
        return filter;
    }

    public WorklogFilter withProjectId(String projectId) {
        WorklogFilter filter = copy();
        filter.projectId = projectId;
        return filter;
    }

    public WorklogFilter withEmployeeId(String employeeId) {
        WorklogFilter filter = copy();
        filter.employeeId = employeeId;
        return filter;
    }
}
