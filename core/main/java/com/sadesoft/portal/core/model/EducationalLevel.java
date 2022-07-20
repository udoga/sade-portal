package com.sadesoft.portal.core.model;

public enum EducationalLevel {
    Primary, Secondary, High_School, Associate, Bachelor, Master, Doctorate, None;

    public static EducationalLevel from(String s) {
        return s.isEmpty()? null : EducationalLevel.valueOf(s);
    }
}
