package com.sadesoft.portal.core.filter;

public class ClientFilter implements Cloneable {
    public Integer offset;

    public ClientFilter() {
    }

    public ClientFilter copy() {
        try {
            return (ClientFilter) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public ClientFilter withOffset(Integer offset) {
        ClientFilter filter = copy();
        filter.offset = offset;
        return filter;
    }
}
