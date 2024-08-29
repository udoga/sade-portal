package com.sadesoft.portal.core.query;

public class EmployeeQuery implements Cloneable {
    public Integer offset;
    public String userId;

    public EmployeeQuery() {
    }

    public EmployeeQuery copy() {
        try {
            return (EmployeeQuery) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public EmployeeQuery withOffset(Integer offset) {
        EmployeeQuery copy = copy();
        copy.offset = offset;
        return copy;
    }

    public EmployeeQuery withUserId(String userId) {
        EmployeeQuery copy = copy();
        copy.userId = userId;
        return copy;
    }
}
