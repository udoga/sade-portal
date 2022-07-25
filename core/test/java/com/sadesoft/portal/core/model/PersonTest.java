package com.sadesoft.portal.core.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    Person validPerson;

    @BeforeEach
    public void setup() {
        validPerson = new Person(1, "firstName", "lastName", "sample@email.com",
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
        assertEquals(1, validPerson.id);
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
        assertNull(validPerson.withId(null).id);
        assertNull(validPerson.withFirstName(null).firstName);
        assertNull(validPerson.withLastName(null).lastName);
        assertNull(validPerson.withEmail(null).email);
        assertNull(validPerson.withWorkEmail(null).workEmail);
        assertNull(validPerson.withMobilePhone(null).mobilePhone);
        assertNull(validPerson.withFirstName(null).firstName);
        assertNull(validPerson.withEmploymentStartDate(null).employmentStartDate);
        assertNull(validPerson.withContractType(null).contractType);
        assertNull(validPerson.withContractEndDate(null).contractEndDate);
        assertNull(validPerson.withWorkingDayId(null).workingDayId);
        assertNull(validPerson.withAccessType(null).accessType);
        assertNull(validPerson.withBirthday(null).birthday);
        assertNull(validPerson.withBloodType(null).bloodType);
        assertNull(validPerson.withEducationalLevel(null).educationalLevel);
        assertNull(validPerson.withEducationStatus(null).educationStatus);
        assertNull(validPerson.withEducationalInstitution(null).educationalInstitution);
        assertNull(validPerson.withGender(null).gender);
        assertNull(validPerson.withIdNumber(null).idNumber);
        assertNull(validPerson.withLevelOfDisability(null).levelOfDisability);
        assertNull(validPerson.withMaritalStatus(null).maritalStatus);
        assertNull(validPerson.withPartnerWorkStatus(null).partnerWorkStatus);
        assertNull(validPerson.withMilitaryService(null).militaryService);
        assertNull(validPerson.withMilitaryServiceDelayDate(null).militaryServiceDelayDate);
        assertNull(validPerson.withNationality(null).nationality);
        assertNull(validPerson.withNumberOfChildren(null).numberOfChildren);
        assertNull(validPerson.withTourStatus(null).tourStatus);
    }
}
