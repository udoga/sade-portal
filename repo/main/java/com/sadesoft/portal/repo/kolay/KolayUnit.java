package com.sadesoft.portal.repo.kolay;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class KolayUnit {
    public String startDate;
    public String endDate;
    public String employmentType;
    public String managerId;
    @JsonProperty("default")
    public String default_;
    public String active;
    public List<KolayUnitItem> items;

    public KolayUnit() {
    }
}
