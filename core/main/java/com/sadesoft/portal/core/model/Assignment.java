package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;

import java.util.Objects;

public class Assignment implements Record {
    public String id;
    public String userId;
    public String roleId;

    public Assignment() {
    }

    public Assignment(String id, String userId, String roleId) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
    }

    public Assignment(Assignment a) {
        this(a.id, a.userId, a.roleId);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Assignment)) return false;
        Assignment r = (Assignment) o;
        return Objects.equals(id, r.id) &&
                Objects.equals(userId, r.userId) &&
                Objects.equals(roleId, r.roleId);
    }

    @Override
    public String toString() {
        return "Assignment[" +
                "id=" + id +
                " userId=" + userId +
                " roleId=" + roleId +
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
    public Assignment copy() {
        try {
            return (Assignment) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Assignment withId(String id) {
        Assignment copy = copy();
        copy.id = id;
        return copy;
    }

    public Assignment withUserId(String userId) {
        Assignment copy = copy();
        copy.userId = userId;
        return copy;
    }

    public Assignment withRoleId(String roleId) {
        Assignment copy = copy();
        copy.roleId = roleId;
        return copy;
    }
}
