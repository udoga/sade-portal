package com.sadesoft.portal.core.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    Person validPerson;

    @BeforeEach
    public void setup() {
        validPerson = new Person(0, "firstName", "lastName", "sample@email.com",
                "sample@workmail.com", "05555555555", "05355353535", LocalDate.of(2022,1,1),
                ContractType.Definite , LocalDate.of(2023,1,1), "123", AccessType.Employee,
                LocalDate.of(2000,1,1), BloodType.APositive,
                EducationalLevel.Bachelor, EducationStatus.Student, "YTU",
                Gender.Male, "12345678910", LevelOfDisability.NotDisabled,
                MaritalStatus.Single, false, MilitaryService.Uncompleted,
                LocalDate.of(2024,1,1), "TR", 0, TourStatus.Completed);
    }

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
        assertNull(validPerson.id);
        assertEquals("firstName", validPerson.firstName);
        assertEquals("lastName", validPerson.lastName);
        assertEquals("sample@email.com", validPerson.email);
        assertEquals("sample@workmail.com", validPerson.workEmail);
        assertEquals("05555555555", validPerson.mobilePhone);
        assertEquals("05355353535", validPerson.workPhone);
        assertEquals(LocalDate.of(2022,1,1), validPerson.employmentStartDate);
        assertEquals(ContractType.Definite, validPerson.contractType);
        assertEquals(LocalDate.of(2023,1,1), validPerson.contractEndDate);
        assertEquals("123", validPerson.workingDayId);
        assertEquals(AccessType.Employee, validPerson.accessType);
        assertEquals(LocalDate.of(2000,1,1), validPerson.birthday);
        assertEquals(BloodType.APositive, validPerson.bloodType);
        assertEquals(EducationalLevel.Bachelor, validPerson.educationalLevel);
        assertEquals(EducationStatus.Student, validPerson.educationStatus);
        assertEquals("YTU", validPerson.educationalInstitution);
        assertEquals(Gender.Male, validPerson.gender);
        assertEquals("12345678910", validPerson.idNumber);
        assertEquals(LevelOfDisability.NotDisabled, validPerson.levelOfDisability);
        assertEquals(MaritalStatus.Single, validPerson.maritalStatus);
        assertEquals(false, validPerson.partnerWorkStatus);
        assertEquals(MilitaryService.Uncompleted, validPerson.militaryService);
        assertEquals(LocalDate.of(2024,1,1), validPerson.militaryServiceDelayDate);
        assertEquals("TR", validPerson.nationality);
        assertEquals(0, validPerson.numberOfChildren);
        assertEquals(TourStatus.Completed, validPerson.tourStatus);
    }

    @Test
    public void clonesWithFields() {
        assertNull(validPerson.withId(1).id);
        assertNull(validPerson.withFirstName("new_first_name").firstName);
        assertNull(validPerson.withLastName("new_last_name").lastName);
        assertNull(validPerson.withEmail("new_email").email);
        assertNull(validPerson.withWorkEmail("new_work_email").workEmail);
        assertNull(validPerson.withMobilePhone("new_mobile_phone").mobilePhone);
        assertNull(validPerson.withFirstName("new_work_phone").firstName);
        assertNull(validPerson.withEmploymentStartDate(LocalDate.of(2022,2,2)).employmentStartDate);
        assertNull(validPerson.withContractType(ContractType.Indefinite).contractType);
        assertNull(validPerson.withContractEndDate(LocalDate.of(2023,2,2)).contractEndDate);
        assertNull(validPerson.withWorkingDayId("new_working_day_id").workingDayId);
        assertNull(validPerson.withAccessType(AccessType.Manager).accessType);
        assertNull(validPerson.withBirthday(LocalDate.of(2000,2,2)).birthday);
        assertNull(validPerson.withBloodType(BloodType.ABNegative).bloodType);
        assertNull(validPerson.withEducationalLevel(EducationalLevel.Associate).educationalLevel);
        assertNull(validPerson.withEducationStatus(EducationStatus.Graduate).educationStatus);
        assertNull(validPerson.withEducationalInstitution("ITU").educationalInstitution);
        assertNull(validPerson.withGender(Gender.Female).gender);
        assertNull(validPerson.withIdNumber("10987654321").idNumber);
        assertNull(validPerson.withLevelOfDisability(LevelOfDisability.FirstLevel).levelOfDisability);
        assertNull(validPerson.withMaritalStatus(MaritalStatus.Married).maritalStatus);
        assertNull(validPerson.withPartnerWorkStatus(true).partnerWorkStatus);
        assertNull(validPerson.withMilitaryService(MilitaryService.Delayed).militaryService);
        assertNull(validPerson.withMilitaryServiceDelayDate(LocalDate.of(2024,2,2)).militaryServiceDelayDate);
        assertNull(validPerson.withNationality("new_nationality").nationality);
        assertNull(validPerson.withNumberOfChildren(1).numberOfChildren);
        assertNull(validPerson.withTourStatus(TourStatus.Active).tourStatus);
        assertNull(validPerson.withFirstName("new_first_name").id);
        assertNull(validPerson.withFirstName(null).firstName);
    }
}
