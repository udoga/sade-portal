package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;

import java.util.Objects;

public class Activity implements Record {
    public String id;
    public String name;

    public Activity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Activity(){}

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Activity)) return false;
        Activity w = (Activity) o;
        return Objects.equals(id, w.id) && Objects.equals(name, w.name);
    }

    @Override
    public String toString() {
        return "Activity[" +
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

    public Activity copy() {
        try {
            return (Activity) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Activity withId(String id) {
        Activity activity = copy();
        activity.id = id;
        return activity;
    }

    public Activity withName(String name) {
        Activity activity = copy();
        activity.name = name;
        return activity;
    }
}
