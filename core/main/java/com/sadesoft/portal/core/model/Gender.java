package com.sadesoft.portal.core.model;

public enum Gender {
    Female, Male, Unspecified;

    public static Gender from(String s) {
        return s.isEmpty()? null : Gender.valueOf(s);
    }
}
