package com.sadesoft.portal.core.query;

public class VacationQuery implements Cloneable {
    public String status;
    public String employeeId;

    public VacationQuery() {
    }

    public VacationQuery copy() {
        try {
            return (VacationQuery) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public VacationQuery withStatus(String status) {
        VacationQuery copy = copy();
        copy.status = status;
        return copy;
    }

    public VacationQuery withEmployeeId(String employeeId) {
        VacationQuery copy = copy();
        copy.employeeId = employeeId;
        return copy;
    }
}