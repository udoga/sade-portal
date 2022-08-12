package com.sadesoft.portal.core.sample;

import java.time.LocalDate;
import com.sadesoft.portal.core.model.*;

public class PersonSamples {
    public static Person validPerson = new Person(
            null,
            "firstName",
            "lastName",
            "sample@email.com",
            "sample@workmail.com",
            "+90 5555555555",
            "+90 5355353535",
            LocalDate.of(2022, 1, 1),
            LocalDate.of(2023, 1, 1),
            "123",
            AccessType.Employee,
            LocalDate.of(2000, 1, 1),
            BloodType.APositive,
            EducationLevel.Bachelor,
            EducationStatus.Student,
            "YTU",
            Gender.Male,
            "12345678910",
            LevelOfDisability.NotDisabled,
            MaritalStatus.Single,
            false,
            MilitaryService.Uncompleted,
            LocalDate.of(2024,1,1),
            "TR",
            0,
            TourStatus.Completed);
}
