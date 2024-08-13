package com.sadesoft.portal.core.filter;

public class AssignmentFilter implements Cloneable {
    public String userId;
    public String roleId;

    public AssignmentFilter() {
    }

    public AssignmentFilter copy() {
        try {
            return (AssignmentFilter) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public AssignmentFilter withUserId(String userId) {
        AssignmentFilter filter = copy();
        filter.userId = userId;
        return filter;
    }

    public AssignmentFilter withRoleId(String roleId) {
        AssignmentFilter filter = copy();
        filter.roleId = roleId;
        return filter;
    }
}
