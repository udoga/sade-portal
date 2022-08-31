package com.sadesoft.portal.repo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sadesoft.portal.core.model.*;
import com.sadesoft.portal.repo.kolay.KolayPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class KolayPersonTest {
    private KolayPerson kolayPerson;
    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        kolayPerson = new KolayPerson( null,
            "firstName",
            "lastName",
            "sample@email.com",
            "sample@workmail.com",
            "+90 5555555555",
            "+90 5355353535",
            "male",
            "employee",
            "TR",
            "12345678910",
            "2022-01-01",
            "definite",
            "2023-01-01",
            "2000-01-01",
            "single",
            false,
            "uncompleted",
            "2024-01-01",
            "bachelor",
            "student",
            "YTU",
            "active",
            "completed",
            "2022-02-04 11:09:19",
            "2022-02-04 11:09:19",
            "notDisabled",
            0,
            "A+");
    }

    @Test
    public void initializesWithFields() {
        assertNull(kolayPerson.id);
        assertEquals("firstName",           kolayPerson.firstName);
        assertEquals("lastName",            kolayPerson.lastName);
        assertEquals("sample@email.com",    kolayPerson.email);
        assertEquals("sample@workmail.com", kolayPerson.workEmail);
        assertEquals("+90 5555555555",      kolayPerson.mobilePhone);
        assertEquals("+90 5355353535",      kolayPerson.workPhone);
        assertEquals("male",                kolayPerson.gender);
        assertEquals("employee",            kolayPerson.accessType);
        assertEquals("TR",                  kolayPerson.nationality);
        assertEquals("12345678910",         kolayPerson.idNumber);
        assertEquals("2022-01-01",          kolayPerson.employmentStartDate);
        assertEquals("definite",            kolayPerson.contractType);
        assertEquals("2023-01-01",          kolayPerson.contractEndDate);
        assertEquals("2000-01-01",          kolayPerson.birthday);
        assertEquals("single",              kolayPerson.maritalStatus);
        assertEquals(false,                 kolayPerson.partnerWorkStatus);
        assertEquals("uncompleted",         kolayPerson.militaryService);
        assertEquals("2024-01-01",          kolayPerson.militaryServiceDelayDate);
        assertEquals("bachelor",            kolayPerson.educationLevel);
        assertEquals("student",             kolayPerson.educationStatus);
        assertEquals("YTU",                 kolayPerson.educationalInstitution );
        assertEquals("active",              kolayPerson.status);
        assertEquals("completed",           kolayPerson.tourStatus);
        assertEquals("2022-02-04 11:09:19", kolayPerson.createdAt);
        assertEquals("2022-02-04 11:09:19", kolayPerson.updatedAt);
        assertEquals("notDisabled",         kolayPerson.levelOfDisability);
        assertEquals(0,                     kolayPerson.numberOfChildren);
        assertEquals("A+",                  kolayPerson.bloodType);
    }

    @Test
    public void initializesWithoutFields() {
        KolayPerson kp = new KolayPerson();
        assertNull(kp.id);
        assertNull(kp.firstName);
        assertNull(kp.lastName);
        assertNull(kp.email);
        assertNull(kp.workEmail);
        assertNull(kp.mobilePhone);
        assertNull(kp.workPhone);
        assertNull(kp.gender);
        assertNull(kp.accessType);
        assertNull(kp.nationality);
        assertNull(kp.idNumber);
        assertNull(kp.employmentStartDate);
        assertNull(kp.contractType);
        assertNull(kp.contractEndDate);
        assertNull(kp.birthday);
        assertNull(kp.maritalStatus);
        assertNull(kp.partnerWorkStatus);
        assertNull(kp.militaryService);
        assertNull(kp.militaryServiceDelayDate);
        assertNull(kp.educationLevel);
        assertNull(kp.educationStatus);
        assertNull(kp.educationalInstitution);
        assertNull(kp.status);
        assertNull(kp.tourStatus);
        assertNull(kp.createdAt);
        assertNull(kp.updatedAt);
        assertNull(kp.levelOfDisability);
        assertNull(kp.numberOfChildren);
        assertNull(kp.bloodType);
    }

    @Test
    public void printsFields() {
        assertEquals("KolayPerson[id=null" +
                " firstName=firstName" +
                " lastName=lastName" +
                " email=sample@email.com" +
                " workEmail=sample@workmail.com" +
                " mobilePhone=+90 5555555555" +
                " workPhone=+90 5355353535" +
                " gender=male" +
                " accessType=employee" +
                " nationality=TR" +
                " idNumber=12345678910" +
                " employmentStartDate=2022-01-01" +
                " contractType=definite" +
                " contractEndDate=2023-01-01" +
                " birthday=2000-01-01" +
                " maritalStatus=single" +
                " partnerWorkStatus=false" +
                " militaryService=uncompleted" +
                " militaryServiceDelayDate=2024-01-01" +
                " educationLevel=bachelor" +
                " educationStatus=student" +
                " educationalInstitution=YTU" +
                " status=active" +
                " tourStatus=completed" +
                " createdAt=2022-02-04 11:09:19" +
                " updatedAt=2022-02-04 11:09:19" +
                " levelOfDisability=notDisabled" +
                " numberOfChildren=0" +
                " bloodType=A+" +
                "]", kolayPerson.toString());
    }

    @Test
    public void clonesWithFields() {
        assertNull(kolayPerson.withId(null).id);
        assertNull(kolayPerson.withFirstName(null).firstName);
        assertNull(kolayPerson.withLastName(null).lastName);
        assertNull(kolayPerson.withEmail(null).email);
        assertNull(kolayPerson.withWorkEmail(null).workEmail);
        assertNull(kolayPerson.withMobilePhone(null).mobilePhone);
        assertNull(kolayPerson.withWorkPhone(null).workPhone);
        assertNull(kolayPerson.withGender(null).gender);
        assertNull(kolayPerson.withAccessType(null).accessType);
        assertNull(kolayPerson.withNationality(null).nationality);
        assertNull(kolayPerson.withIdNumber(null).idNumber);
        assertNull(kolayPerson.withEmploymentStartDate(null).employmentStartDate);
        assertNull(kolayPerson.withContractType(null).contractType);
        assertNull(kolayPerson.withContractEndDate(null).contractEndDate);
        assertNull(kolayPerson.withBirthday(null).birthday);
        assertNull(kolayPerson.withMaritalStatus(null).maritalStatus);
        assertNull(kolayPerson.withPartnerWorkStatus(null).partnerWorkStatus);
        assertNull(kolayPerson.withMilitaryService(null).militaryService);
        assertNull(kolayPerson.withMilitaryServiceDelayDate(null).militaryServiceDelayDate);
        assertNull(kolayPerson.withEducationLevel(null).educationLevel);
        assertNull(kolayPerson.withEducationStatus(null).educationStatus);
        assertNull(kolayPerson.withEducationalInstitution(null).educationalInstitution);
        assertNull(kolayPerson.withStatus(null).status);
        assertNull(kolayPerson.withTourStatus(null).tourStatus);
        assertNull(kolayPerson.withCreatedAt(null).createdAt);
        assertNull(kolayPerson.withUpdatedAt(null).updatedAt);
        assertNull(kolayPerson.withLevelOfDisability(null).levelOfDisability);
        assertNull(kolayPerson.withNumberOfChildren(null).numberOfChildren);
        assertNull(kolayPerson.withBloodType(null).bloodType);
    }

    @Test
    public void checksEquality() {
        assertEquals(kolayPerson, kolayPerson.copy());
        assertNotEquals(kolayPerson, kolayPerson.withId("0"));
        assertNotEquals(kolayPerson, kolayPerson.withFirstName(null));
        assertNotEquals(kolayPerson, kolayPerson.withLastName(null));
        assertNotEquals(kolayPerson, kolayPerson.withEmail(null));
        assertNotEquals(kolayPerson, kolayPerson.withWorkEmail(null));
        assertNotEquals(kolayPerson, kolayPerson.withMobilePhone(null));
        assertNotEquals(kolayPerson, kolayPerson.withWorkPhone(null));
        assertNotEquals(kolayPerson, kolayPerson.withGender(null));
        assertNotEquals(kolayPerson, kolayPerson.withAccessType(null));
        assertNotEquals(kolayPerson, kolayPerson.withNationality(null));
        assertNotEquals(kolayPerson, kolayPerson.withIdNumber(null));
        assertNotEquals(kolayPerson, kolayPerson.withEmploymentStartDate(null));
        assertNotEquals(kolayPerson, kolayPerson.withContractType(null));
        assertNotEquals(kolayPerson, kolayPerson.withContractEndDate(null));
        assertNotEquals(kolayPerson, kolayPerson.withBirthday(null));
        assertNotEquals(kolayPerson, kolayPerson.withMaritalStatus(null));
        assertNotEquals(kolayPerson, kolayPerson.withPartnerWorkStatus(null));
        assertNotEquals(kolayPerson, kolayPerson.withMilitaryService(null));
        assertNotEquals(kolayPerson, kolayPerson.withMilitaryServiceDelayDate(null));
        assertNotEquals(kolayPerson, kolayPerson.withEducationLevel(null));
        assertNotEquals(kolayPerson, kolayPerson.withEducationStatus(null));
        assertNotEquals(kolayPerson, kolayPerson.withEducationalInstitution(null));
        assertNotEquals(kolayPerson, kolayPerson.withTourStatus(null));
        assertNotEquals(kolayPerson, kolayPerson.withStatus(null));
        assertNotEquals(kolayPerson, kolayPerson.withCreatedAt(null));
        assertNotEquals(kolayPerson, kolayPerson.withUpdatedAt(null));
        assertNotEquals(kolayPerson, kolayPerson.withLevelOfDisability(null));
        assertNotEquals(kolayPerson, kolayPerson.withNumberOfChildren(null));
        assertNotEquals(kolayPerson, kolayPerson.withBloodType(null));
    }

    @Test
    void convertsToPerson() {
        assertEquals(new Person().withId("1"), new KolayPerson().withId("1").toPerson());
        assertEquals(new Person().withFirstName("a"), new KolayPerson().withFirstName("a").toPerson());
        assertEquals(new Person().withLastName("a"), new KolayPerson().withLastName("a").toPerson());
        assertEquals(new Person().withEmail("a@gmail.com"), new KolayPerson().withEmail("a@gmail.com").toPerson());
        assertEquals(new Person().withWorkEmail("a2@gmail.com"), new KolayPerson().withWorkEmail("a2@gmail.com").toPerson());
        assertEquals(new Person().withMobilePhone("+90 5320000000"), new KolayPerson().withMobilePhone("+905320000000").toPerson());
        assertEquals(new Person().withWorkPhone("+90 5320000001"), new KolayPerson().withWorkPhone("+905320000001").toPerson());
        assertEquals(new Person().withEmploymentStartDate(LocalDate.parse("2020-08-13")), new KolayPerson().withEmploymentStartDate("2020-08-13").toPerson());
        assertEquals(new Person().withContractEndDate(LocalDate.parse("2020-08-13")), new KolayPerson().withContractEndDate("2020-08-13").toPerson());
        assertEquals(new Person().withAccessType(AccessType.Employee), new KolayPerson().withAccessType("employee").toPerson());
        assertEquals(new Person().withBirthday(LocalDate.parse("2020-08-13")), new KolayPerson().withBirthday("2020-08-13").toPerson());
        assertEquals(new Person().withBloodType(BloodType.ABNegative), new KolayPerson().withBloodType("AB-").toPerson());
        assertEquals(new Person().withEducationLevel(EducationLevel.Bachelor), new KolayPerson().withEducationLevel("bachelor").toPerson());
        assertEquals(new Person().withGraduated(false), new KolayPerson().withEducationStatus("student").toPerson());
        assertEquals(new Person().withEducationalInstitution("a"), new KolayPerson().withEducationalInstitution("a").toPerson());
        assertEquals(new Person().withGender(Gender.Male), new KolayPerson().withGender("male").toPerson());
        assertEquals(new Person().withIdNumber("12"), new KolayPerson().withIdNumber("12").toPerson());
        assertEquals(new Person().withLevelOfDisability(LevelOfDisability.NotDisabled), new KolayPerson().withLevelOfDisability("notDisabled").toPerson());
        assertEquals(new Person().withMaritalStatus(MaritalStatus.Single), new KolayPerson().withMaritalStatus("single").toPerson());
        assertEquals(new Person().withPartnerWorkStatus(false), new KolayPerson().withPartnerWorkStatus(false).toPerson());
        assertEquals(new Person().withMilitaryService(MilitaryService.Exempt), new KolayPerson().withMilitaryService("exempt").toPerson());
        assertEquals(new Person().withMilitaryServiceDelayDate(LocalDate.parse("2020-08-13")), new KolayPerson().withMilitaryServiceDelayDate("2020-08-13").toPerson());
        assertEquals(new Person().withNationality("TR"), new KolayPerson().withNationality("TR").toPerson());
        assertEquals(new Person().withNumberOfChildren(2), new KolayPerson().withNumberOfChildren(2).toPerson());
        assertEquals(new Person().withTourStatus(TourStatus.Completed), new KolayPerson().withTourStatus("completed").toPerson());
    }

    @Test
    void convertsBloodType() {
        assertEquals(BloodType.ANegative,    kolayPerson.toBloodType("A-"));
        assertEquals(BloodType.APositive,    kolayPerson.toBloodType("A+"));
        assertEquals(BloodType.ZeroNegative, kolayPerson.toBloodType("0-"));
        assertEquals(BloodType.ZeroPositive, kolayPerson.toBloodType("0+"));
        assertEquals(BloodType.ABNegative,   kolayPerson.toBloodType("AB-"));
        assertEquals(BloodType.ABPositive,   kolayPerson.toBloodType("AB+"));
        assertEquals(BloodType.BNegative,    kolayPerson.toBloodType("B-"));
        assertEquals(BloodType.BPositive,    kolayPerson.toBloodType("B+"));
    }

    @Test
    public void convertsToJson() {
        assertEquals(mapper.valueToTree(kolayPerson), kolayPerson.toJson());
    }

    @Test
    public void convertsFromJson() {
        assertEquals(kolayPerson, KolayPerson.fromJson(mapper.valueToTree(kolayPerson)));
    }
}
