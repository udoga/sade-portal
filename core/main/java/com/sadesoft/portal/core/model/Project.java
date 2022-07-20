package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;

public class Project implements Record {
    public Integer id = null;
    public String name = null;

    public Project() {
    }

    public Project(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Project[id=" + id + " name=" + name + "]";
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
