package com.sadesoft.portal.base.repo;

public interface Record extends Cloneable {
    String getId();
    void setId(String id);
    Record copy();
}
