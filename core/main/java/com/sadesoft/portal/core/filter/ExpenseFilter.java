package com.sadesoft.portal.core.filter;

public class ExpenseFilter implements Cloneable {
    public String employeeId;

    public ExpenseFilter() {
    }

    public ExpenseFilter copy() {
        try {
            return (ExpenseFilter) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public ExpenseFilter withEmployeeId(String employeeId) {
        ExpenseFilter filter = copy();
        filter.employeeId = employeeId;
        return filter;
    }
}
