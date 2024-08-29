package com.sadesoft.portal.core.query;

public class ProjectQuery implements Cloneable {
    public String clientId;
    public Integer offset;

    public ProjectQuery() {
    }

    public ProjectQuery copy() {
        try {
            return (ProjectQuery) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public ProjectQuery withClientId(String clientId) {
        ProjectQuery copy = copy();
        copy.clientId = clientId;
        return copy;
    }

    public ProjectQuery withOffset(Integer offset) {
        ProjectQuery copy = copy();
        copy.offset = offset;
        return copy;
    }
}
