package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;

import java.util.Objects;

public class Permission implements Record {
    public String id;
    public String name;

    public Permission(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Permission(){}

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Permission)) return false;
        Permission p = (Permission) o;
        return Objects.equals(id, p.id) && Objects.equals(name, p.name);
    }

    @Override
    public String toString() {
        return "Permission[" +
                "id=" + id +
                " name=" + name +
                ']';
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Permission copy() {
        try {
            return (Permission) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Permission withId(String id) {
        Permission permission = copy();
        permission.id = id;
        return permission;
    }

    public Permission withName(String name) {
        Permission permission = copy();
        permission.name = name;
        return permission;
    }
}
