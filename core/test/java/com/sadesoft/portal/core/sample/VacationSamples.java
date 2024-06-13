package com.sadesoft.portal.core.sample;

import com.sadesoft.portal.core.model.Vacation;
import com.sadesoft.portal.core.model.VacationType;

import java.time.LocalDateTime;

public class VacationSamples {
    public static Vacation validVacation = new Vacation(
            null,
            "4",
            VacationType.AnnualVacation,
            "6",
            LocalDateTime.of(2022,5,4,9,18),
            LocalDateTime.of(2022,5,4,9,19),
            5,
            "descriptionExample",
            null);
}
