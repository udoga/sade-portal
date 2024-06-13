package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;

import java.util.Objects;

public class Role implements Record {
    public String id;
    public String name;

    public Role(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role(){}

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Role)) return false;
        Role r = (Role) o;
        return Objects.equals(id, r.id) && Objects.equals(name, r.name);
    }

    @Override
    public String toString() {
        return "Role[" +
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

    public Role copy() {
        try {
            return (Role) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Role withId(String id) {
        Role role = copy();
        role.id = id;
        return role;
    }

    public Role withName(String name) {
        Role role = copy();
        role.name = name;
        return role;
    }
}
