package com.sadesoft.portal.core.filter;

public class ProjectFilter implements Cloneable {
    public String clientId;
    public Integer offset;

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

    public ProjectFilter withOffset(Integer offset) {
        ProjectFilter filter = copy();
        filter.offset = offset;
        return filter;
    }
}
