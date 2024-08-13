package com.sadesoft.portal.core.detailed;

import com.sadesoft.portal.core.model.Grant;
import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.model.Permission;

public class DetailedGrant extends Grant {
    public Role role;
    public Permission permission;

    public DetailedGrant(Grant a) {
        super(a);
    }

    public DetailedGrant(Grant a, Role r, Permission p) {
        super(a);
        this.role = r;
        this.permission = p;
    }

    @Override
    public DetailedGrant copy() {
        try {
            return (DetailedGrant) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public DetailedGrant withRole(Role role) {
        DetailedGrant copy = copy();
        copy.role = role;
        return copy;
    }

    public DetailedGrant withPermission(Permission permission) {
        DetailedGrant copy = copy();
        copy.permission = permission;
        return copy;
    }
}
