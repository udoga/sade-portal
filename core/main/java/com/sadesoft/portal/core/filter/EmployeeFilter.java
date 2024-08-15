package com.sadesoft.portal.core.filter;

public class EmployeeFilter implements Cloneable {
    public String afterId;
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

    public EmployeeFilter withAfterId(String afterId) {
        EmployeeFilter filter = copy();
        filter.afterId = afterId;
        return filter;
    }

    public EmployeeFilter withUserId(String userId) {
        EmployeeFilter filter = copy();
        filter.userId = userId;
        return filter;
    }
}
