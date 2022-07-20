package com.sadesoft.portal.core.model;

public enum MaritalStatus {
    Married, Single, Divorced, Unspecified;

    public static MaritalStatus from(String s) {
        return s.isEmpty()? null : MaritalStatus.valueOf(s);
    }
}
