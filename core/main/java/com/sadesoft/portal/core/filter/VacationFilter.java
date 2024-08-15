package com.sadesoft.portal.core.filter;

public class VacationFilter implements Cloneable {
    public String status;
    public String employeeId;

    public VacationFilter() {
    }

    public VacationFilter copy() {
        try {
            return (VacationFilter) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public VacationFilter withStatus(String status) {
        VacationFilter filter = copy();
        filter.status = status;
        return filter;
    }

    public VacationFilter withEmployeeId(String employeeId) {
        VacationFilter filter = copy();
        filter.employeeId = employeeId;
        return filter;
    }
}