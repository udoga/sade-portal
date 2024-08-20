package com.sadesoft.portal.core.filter;

public class EmployeeFilter implements Cloneable {
    public Integer offset;
    public String userId;

    public EmployeeFilter() {
    }

    public EmployeeFilter copy() {
        try {
            return (EmployeeFilter) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public EmployeeFilter withOffset(Integer offset) {
        EmployeeFilter filter = copy();
        filter.offset = offset;
        return filter;
    }

    public EmployeeFilter withUserId(String userId) {
        EmployeeFilter filter = copy();
        filter.userId = userId;
        return filter;
    }
}
