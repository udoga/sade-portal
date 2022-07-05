package com.sadesoft.portal.core.model;

public enum ItemEnum {
    ValueOne, ValueTwo, ValueThree;

    public static ItemEnum from(String s) {
        return s.isEmpty()? null : ItemEnum.valueOf(s);
    }
}
