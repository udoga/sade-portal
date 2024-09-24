package com.sadesoft.portal.core.query;

public class ClientQuery implements Cloneable {
    public Integer offset;
    public String searchName;

    public ClientQuery() {
    }

    public ClientQuery copy() {
        try {
            return (ClientQuery) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public ClientQuery withOffset(Integer offset) {
        ClientQuery copy = copy();
        copy.offset = offset;
        return copy;
    }

    public ClientQuery withSearchName(String searchName) {
        ClientQuery copy = copy();
        copy.searchName = searchName;
        return copy;
    }
}
