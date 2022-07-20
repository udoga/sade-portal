package com.sadesoft.portal.core.model;

public enum BloodType {
    ANegative, APositive, OPositive, ONegative, ABNegative, ABPositive, BNegative, BPositive;

    public static BloodType from(String s) {
        return s.isEmpty()? null : BloodType.valueOf(s);
    }
}
