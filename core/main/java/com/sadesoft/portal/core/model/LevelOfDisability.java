package com.sadesoft.portal.core.model;

public enum LevelOfDisability {
    NotDisabled, ThirdLeve, SecondLevel, FirstLevel;

    public static LevelOfDisability from(String s) {
        return s.isEmpty()? null : LevelOfDisability.valueOf(s);
    }
}
