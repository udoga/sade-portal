package com.sadesoft.portal.core.model;

public enum MilitaryService {
    Completed, Uncompleted, Exempt, Delayed, Deserter, DraftEvader;

    public static MilitaryService from(String s) {
        return s.isEmpty()? null : MilitaryService.valueOf(s);
    }
}
