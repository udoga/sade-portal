package com.sadesoft.portal.core.model;

public enum EducationStatus {
    Graduate, Student;

    public static EducationStatus from(String s) {
        return s.isEmpty()? null : EducationStatus.valueOf(s);
    }
}
