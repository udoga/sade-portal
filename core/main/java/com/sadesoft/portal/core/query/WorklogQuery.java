package com.sadesoft.portal.core.query;

import com.sadesoft.portal.core.order.WorklogOrder;

import java.time.LocalDate;

public class WorklogQuery implements Cloneable {
    public Integer offset;
    public String projectId;
    public String employeeId;
    public LocalDate minDate;
    public LocalDate maxDate;
    public WorklogOrder order;

    public WorklogQuery() {
    }

    public WorklogQuery copy() {
        try {
            return (WorklogQuery) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public WorklogQuery withOffset(Integer offset) {
        WorklogQuery copy = copy();
        copy.offset = offset;
        return copy;
    }

    public WorklogQuery withProjectId(String projectId) {
        WorklogQuery copy = copy();
        copy.projectId = projectId;
        return copy;
    }

    public WorklogQuery withEmployeeId(String employeeId) {
        WorklogQuery copy = copy();
        copy.employeeId = employeeId;
        return copy;
    }

    public WorklogQuery withMinDate(LocalDate minDate) {
        WorklogQuery copy = copy();
        copy.minDate = minDate;
        return copy;
    }

    public WorklogQuery withMaxDate(LocalDate maxDate) {
        WorklogQuery copy = copy();
        copy.maxDate = maxDate;
        return copy;
    }

    public WorklogQuery withOrder(WorklogOrder order) {
        WorklogQuery copy = copy();
        copy.order = order;
        return copy;
    }
}
