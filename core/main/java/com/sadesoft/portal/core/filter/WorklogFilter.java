package com.sadesoft.portal.core.filter;

public class WorklogFilter implements Cloneable {
    public String afterId;

    public WorklogFilter() {
    }

    public WorklogFilter(String afterId) {
        this.afterId = afterId;
    }

    public WorklogFilter copy() {
        try {
            return (WorklogFilter) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public WorklogFilter withAfterId(String afterId) {
        WorklogFilter filter = copy();
        filter.afterId = afterId;
        return filter;
    }
}
