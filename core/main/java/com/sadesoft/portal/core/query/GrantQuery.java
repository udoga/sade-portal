package com.sadesoft.portal.core.query;

public class GrantQuery implements Cloneable {
    public Integer offset;
    public String roleId;
    public String permissionId;

    public GrantQuery() {
    }

    public GrantQuery copy() {
        try {
            return (GrantQuery) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public GrantQuery withOffset(Integer offset) {
        GrantQuery copy = copy();
        copy.offset = offset;
        return copy;
    }

    public GrantQuery withRoleId(String roleId) {
        GrantQuery copy = copy();
        copy.roleId = roleId;
        return copy;
    }

    public GrantQuery withPermissionId(String permissionId) {
        GrantQuery copy = copy();
        copy.permissionId = permissionId;
        return copy;
    }
}
