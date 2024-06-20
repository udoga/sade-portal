package com.sadesoft.portal.core.sample;

import java.time.LocalDate;

import com.sadesoft.portal.core.model.Worklog;

public class WorklogSamples {
    public static Worklog validWorklog = new Worklog(
            null,
            "1",
            "1",
            "1",
            LocalDate.of(2024, 1, 1),
            2,
            "Approved",
            "Sample Worklog");
}
