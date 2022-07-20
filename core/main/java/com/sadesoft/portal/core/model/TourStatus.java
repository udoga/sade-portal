package com.sadesoft.portal.core.model;

public enum TourStatus {
    Completed, Canceled, Active, Passive;

    public static TourStatus from(String s) {
        return s.isEmpty()? null : TourStatus.valueOf(s);
    }
}
