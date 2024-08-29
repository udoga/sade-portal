package com.sadesoft.portal.core.query;

public class AssignmentQuery implements Cloneable {
    public String userId;
    public String roleId;

    public AssignmentQuery() {
    }

    public AssignmentQuery copy() {
        try {
            return (AssignmentQuery) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public AssignmentQuery withUserId(String userId) {
        AssignmentQuery copy = copy();
        copy.userId = userId;
        return copy;
    }

    public AssignmentQuery withRoleId(String roleId) {
        AssignmentQuery copy = copy();
        copy.roleId = roleId;
        return copy;
    }
}
