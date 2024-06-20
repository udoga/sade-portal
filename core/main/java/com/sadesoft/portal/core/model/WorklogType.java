package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;

import java.util.Objects;

public class WorklogType implements Record {
    public String id;
    public String name;

    public WorklogType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public WorklogType(){}

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof WorklogType)) return false;
        WorklogType w = (WorklogType) o;
        return Objects.equals(id, w.id) && Objects.equals(name, w.name);
    }

    @Override
    public String toString() {
        return "WorklogType[" +
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

    public WorklogType copy() {
        try {
            return (WorklogType) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public WorklogType withId(String id) {
        WorklogType worklogType = copy();
        worklogType.id = id;
        return worklogType;
    }

    public WorklogType withName(String name) {
        WorklogType worklogType = copy();
        worklogType.name = name;
        return worklogType;
    }
}
