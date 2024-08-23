package com.sadesoft.portal.core.filter;

import java.time.LocalDate;

public class WorklogFilter implements Cloneable {
    public Integer offset;
    public String projectId;
    public String employeeId;
    public LocalDate minDate;
    public LocalDate maxDate;

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

    public WorklogFilter withMinDate(LocalDate minDate) {
        WorklogFilter filter = copy();
        filter.minDate = minDate;
        return filter;
    }

    public WorklogFilter withMaxDate(LocalDate maxDate) {
        WorklogFilter filter = copy();
        filter.maxDate = maxDate;
        return filter;
    }
}
