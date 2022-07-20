package com.sadesoft.portal.core.model;

public enum AccessType {
    Employee, Manager, Owner;

    public static AccessType from(String s) {
        return s.isEmpty()? null : AccessType.valueOf(s);
    }
}
