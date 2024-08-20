package com.sadesoft.portal.core.filter;

public class GrantFilter implements Cloneable {
    public Integer offset;
    public String roleId;
    public String permissionId;

    public GrantFilter() {
    }

    public GrantFilter copy() {
        try {
            return (GrantFilter) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public GrantFilter withOffset(Integer offset) {
        GrantFilter filter = copy();
        filter.offset = offset;
        return filter;
    }

    public GrantFilter withRoleId(String roleId) {
        GrantFilter filter = copy();
        filter.roleId = roleId;
        return filter;
    }

    public GrantFilter withPermissionId(String permissionId) {
        GrantFilter filter = copy();
        filter.permissionId = permissionId;
        return filter;
    }
}
