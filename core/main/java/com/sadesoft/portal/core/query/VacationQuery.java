package com.sadesoft.portal.core.query;

public class VacationQuery implements Cloneable {
    public String approvalStatus;
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

    public VacationQuery withApprovalStatus(String approvalStatus) {
        VacationQuery copy = copy();
        copy.approvalStatus = approvalStatus;
        return copy;
    }

    public VacationQuery withEmployeeId(String employeeId) {
        VacationQuery copy = copy();
        copy.employeeId = employeeId;
        return copy;
    }
}