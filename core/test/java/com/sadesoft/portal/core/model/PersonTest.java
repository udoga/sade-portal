package com.sadesoft.portal.core.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    @Test
    public void initializesWithoutFields() {
        Person person = new Person();
        assertNull(person.id);
        assertNull(person.firstName);
        assertNull(person.lastName);
        assertNull(person.email);
        assertNull(person.workEmail);
        assertNull(person.mobilePhone);
        assertNull(person.workPhone);
        assertNull(person.employmentStartDate);
        assertNull(person.contractType);
        assertNull(person.contractEndDate);
        assertNull(person.workingDayId);
        assertNull(person.accessType);
        assertNull(person.birthday);
        assertNull(person.bloodType);
        assertNull(person.educationalLevel);
        assertNull(person.educationStatus);
        assertNull(person.educationalInstitution);
        assertNull(person.gender);
        assertNull(person.idNumber);
        assertNull(person.levelOfDisability);
        assertNull(person.maritalStatus);
        assertNull(person.partnerWorkStatus);
        assertNull(person.militaryService);
        assertNull(person.militaryServiceDelayDate);
        assertNull(person.nationality);
        assertNull(person.numberOfChildren);
        assertNull(person.tourStatus);
    }

    @Test
    public void initializesWithFields() {
        Person person = new Person("firstName", "lastName", "fl@email.com",
                "fl@workmail.com", "05555555555", "05355353535", LocalDate.of(2022,1,1),
                ContractType.Definite , LocalDate.of(2023,1,1), "123", AccessType.Employee,
                LocalDate.of(2000,1,1), BloodType.APositive,
                EducationalLevel.Bachelor, EducationStatus.Student, "YTU",
                Gender.Male, "12345678910", LevelOfDisability.NotDisabled,
                MaritalStatus.Single, false, MilitaryService.Uncompleted,
                LocalDate.of(2024,1,1), "TR", 0, TourStatus.Completed);
        assertNull(person.id);
        assertEquals("firstName", person.firstName);
        assertEquals("lastName", person.lastName);
        assertEquals("fl@email.com", person.email);
        assertEquals("fl@workmail.com", person.workEmail);
        assertEquals("05555555555", person.mobilePhone);
        assertEquals("05355353535", person.workPhone);
        assertEquals(LocalDate.of(2022,1,1), person.employmentStartDate);
        assertEquals(ContractType.Definite, person.contractType);
        assertEquals(LocalDate.of(2023,1,1), person.contractEndDate);
        assertEquals("123", person.workingDayId);
        assertEquals(AccessType.Employee, person.accessType);
        assertEquals(LocalDate.of(2000,1,1), person.birthday);
        assertEquals(BloodType.APositive, person.bloodType);
        assertEquals(EducationalLevel.Bachelor, person.educationalLevel);
        assertEquals(EducationStatus.Student, person.educationStatus);
        assertEquals("YTU", person.educationalInstitution);
        assertEquals(Gender.Male, person.gender);
        assertEquals("12345678910", person.idNumber);
        assertEquals(LevelOfDisability.NotDisabled, person.levelOfDisability);
        assertEquals(MaritalStatus.Single, person.maritalStatus);
        assertEquals(false, person.partnerWorkStatus);
        assertEquals(MilitaryService.Uncompleted, person.militaryService);
        assertEquals(LocalDate.of(2024,1,1), person.militaryServiceDelayDate);
        assertEquals("TR", person.nationality);
        assertEquals(0, person.numberOfChildren);
        assertEquals(TourStatus.Completed, person.tourStatus);
    }

    @Test
    public void clonesWithFields() {
        Person person = new Person("firstName", "lastName", "fl@email.com",
                "fl@workmail.com", "05555555555", "05355353535", LocalDate.of(2022,1,1),
                ContractType.Definite , LocalDate.of(2023,1,1), "123", AccessType.Employee,
                LocalDate.of(2000,1,1), BloodType.APositive,
                EducationalLevel.Bachelor, EducationStatus.Student, "YTU",
                Gender.Male, "12345678910", LevelOfDisability.NotDisabled,
                MaritalStatus.Single, false, MilitaryService.Uncompleted,
                LocalDate.of(2024,1,1), "TR", 0, TourStatus.Completed);
        assertEquals(1, person.withId(1).id);
        assertEquals("new_first_name", person.withFirstName("new_first_name").firstName);
        assertEquals("new_last_name", person.withLastName("new_last_name").lastName);
        assertEquals("new_email", person.withEmail("new_email").email);
        assertEquals("new_work_email", person.withWorkEmail("new_work_email").workEmail);
        assertEquals("new_mobile_phone", person.withMobilePhone("new_mobile_phone").mobilePhone);
        assertEquals("new_work_phone", person.withFirstName("new_work_phone").firstName);
        assertEquals(LocalDate.of(2022,2,2), person.withEmploymentStartDate(LocalDate.of(2022,2,2)).employmentStartDate);
        assertEquals(ContractType.Indefinite, person.withContractType(ContractType.Indefinite).contractType);
        assertEquals(LocalDate.of(2023,2,2), person.withContractEndDate(LocalDate.of(2023,2,2)).contractEndDate);
        assertEquals("new_working_day_id", person.withWorkingDayId("new_working_day_id").workingDayId);
        assertEquals(AccessType.Manager, person.withAccessType(AccessType.Manager).accessType);
        assertEquals(LocalDate.of(2000,2,2), person.withBirthday(LocalDate.of(2000,2,2)).birthday);
        assertEquals(BloodType.ABNegative, person.withBloodType(BloodType.ABNegative).bloodType);
        assertEquals(EducationalLevel.Associate, person.withEducationalLevel(EducationalLevel.Associate).educationalLevel);
        assertEquals(EducationStatus.Graduate, person.withEducationStatus(EducationStatus.Graduate).educationStatus);
        assertEquals("ITU", person.withEducationalInstitution("ITU").educationalInstitution);
        assertEquals(Gender.Female, person.withGender(Gender.Female).gender);
        assertEquals("10987654321", person.withIdNumber("10987654321").idNumber);
        assertEquals(LevelOfDisability.FirstLevel, person.withLevelOfDisability(LevelOfDisability.FirstLevel).levelOfDisability);
        assertEquals(MaritalStatus.Married, person.withMaritalStatus(MaritalStatus.Married).maritalStatus);
        assertEquals(true, person.withPartnerWorkStatus(true).partnerWorkStatus);
        assertEquals(MilitaryService.Delayed, person.withMilitaryService(MilitaryService.Delayed).militaryService);
        assertEquals(LocalDate.of(2024,2,2), person.withMilitaryServiceDelayDate(LocalDate.of(2024,2,2)).militaryServiceDelayDate);
        assertEquals("new_nationality", person.withNationality("new_nationality").nationality);
        assertEquals(1, person.withNumberOfChildren(1).numberOfChildren);
        assertEquals(TourStatus.Active, person.withTourStatus(TourStatus.Active).tourStatus);
        assertNull(person.withFirstName("new_first_name").id);
        assertNull(person.withFirstName(null).firstName);
    }
}
