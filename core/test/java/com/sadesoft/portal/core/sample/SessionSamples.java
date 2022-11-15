package com.sadesoft.portal.core.sample;

import com.sadesoft.portal.core.model.Session;

import java.time.LocalDateTime;

public class SessionSamples {
    public static Session validSession = new Session(
            null,
            "1",
            LocalDateTime.of(2022, 1, 1, 10, 0),
            LocalDateTime.of(2022, 1, 1, 12, 0),
            "sample-token");
}
