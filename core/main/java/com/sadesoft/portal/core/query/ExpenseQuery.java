package com.sadesoft.portal.core.query;

public class ExpenseQuery implements Cloneable {
    public String employeeId;

    public ExpenseQuery() {
    }

    public ExpenseQuery copy() {
        try {
            return (ExpenseQuery) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public ExpenseQuery withEmployeeId(String employeeId) {
        ExpenseQuery copy = copy();
        copy.employeeId = employeeId;
        return copy;
    }
}
