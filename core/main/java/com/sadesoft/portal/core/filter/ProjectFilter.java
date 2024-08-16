package com.sadesoft.portal.core.filter;

public class ProjectFilter implements Cloneable {
    public String clientId;

    public ProjectFilter() {
    }

    public ProjectFilter copy() {
        try {
            return (ProjectFilter) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public ProjectFilter withClientId(String clientId) {
        ProjectFilter filter = copy();
        filter.clientId = clientId;
        return filter;
    }
}
