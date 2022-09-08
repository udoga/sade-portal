package com.sadesoft.portal.repo.kolay;

public class KolayTermination {
    public String personId;
    public String date;
    public String reasonCode;
    public String details;

    public KolayTermination(String personId, String date, String reasonCode, String details) {
        this.personId = personId;
        this.date = date;
        this.reasonCode = reasonCode;
        this.details = details;
    }

    public KolayTermination() {
    }
}
