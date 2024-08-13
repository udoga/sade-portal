package com.sadesoft.portal.core.filter;

public class GrantFilter implements Cloneable {
    public String roleId;
    public String permissionId;
    public String afterId;

    public GrantFilter() {
    }

    public GrantFilter copy() {
        try {
            return (GrantFilter) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
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

    public GrantFilter withAfterId(String afterId) {
        GrantFilter filter = copy();
        filter.afterId = afterId;
        return filter;
    }
}
