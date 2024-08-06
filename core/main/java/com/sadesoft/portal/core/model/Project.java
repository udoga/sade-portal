package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;

import java.util.Objects;

public class Project implements Record {
    public String id;
    public String clientId;
    public String name;
    public String description;

    public Project(){
    }

    public Project(String id, String clientId, String name, String description) {
        this.id = id;
        this.clientId = clientId;
        this.name = name;
        this.description = description;
    }

    public Project(Project p) {
        this(p.id, p.clientId, p.name, p.description);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Project)) return false;
        Project p = (Project) o;
        return Objects.equals(id, p.id) &&
               Objects.equals(clientId, p.clientId) &&
               Objects.equals(name, p.name) &&
               Objects.equals(description, p.description);
    }

    @Override
    public String toString() {
        return "Project[" +
                "id=" + id +
                " clientId=" + clientId +
                " name=" + name +
                " description=" + description +
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

    public Project copy() {
        try {
            return (Project) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Project withId(String id) {
        Project project = copy();
        project.id = id;
        return project;
    }

    public Project withClientId(String clientId) {
        Project project = copy();
        project.clientId = clientId;
        return project;
    }

    public Project withName(String name) {
        Project project = copy();
        project.name = name;
        return project;
    }

    public Project withDescription(String description) {
        Project project = copy();
        project.description = description;
        return project;
    }

    public boolean matches(String clientId) {
        return clientId == null || this.clientId.equals(clientId);
    }
}
