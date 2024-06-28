package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;

import java.util.Objects;

public class Grant implements Record {
    public String id;
    public String roleId;
    public String permissionId;

    public Grant() {
    }

    public Grant(String id, String roleId, String permissionId) {
        this.id = id;
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Grant)) return false;
        Grant r = (Grant) o;
        return Objects.equals(id, r.id) &&
                Objects.equals(roleId, r.roleId) &&
                Objects.equals(permissionId, r.permissionId);
    }

    @Override
    public String toString() {
        return "Grant[" +
                "id=" + id +
                " roleId=" + roleId +
                " permissionId=" + permissionId +
                "]";
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Grant copy() {
        try {
            return (Grant) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Grant withId(String id) {
        Grant copy = copy();
        copy.id = id;
        return copy;
    }
}
