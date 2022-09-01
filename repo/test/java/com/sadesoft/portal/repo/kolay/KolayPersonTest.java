package com.sadesoft.portal.repo.kolay;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sadesoft.portal.base.error.InvalidPhoneError;
import com.sadesoft.portal.core.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class KolayPersonTest {
    private KolayPerson kolayPerson;
    private Person person;
    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        kolayPerson = new KolayPerson(
            "0",
            "firstName",
            "lastName",
            "sample@email.com",
            "sample@workmail.com",
            "+905555555555",
            "+905355353535",
            "male",
            "employee",
            "TR",
            "12345678910",
            "2022-01-01",
            "definite",
            "2023-01-01",
            "2000-01-01",
            "single",
            "notWorking",
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

        person = new Person(
                "0",
                "firstName",
                "lastName",
                "sample@email.com",
                "sample@workmail.com",
                "+90 5555555555",
                "+90 5355353535",
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2023, 1, 1),
                AccessType.Employee,
                LocalDate.of(2000, 1, 1),
                BloodType.APositive,
                EducationLevel.Bachelor,
                false,
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
                true,
                TourStatus.Completed);
    }

    @Test
    public void initializesWithFields() {
        assertEquals("0",                   kolayPerson.id);
        assertEquals("firstName",           kolayPerson.firstName);
        assertEquals("lastName",            kolayPerson.lastName);
        assertEquals("sample@email.com",    kolayPerson.email);
        assertEquals("sample@workmail.com", kolayPerson.workEmail);
        assertEquals("+905555555555",       kolayPerson.mobilePhone);
        assertEquals("+905355353535",       kolayPerson.workPhone);
        assertEquals("male",                kolayPerson.gender);
        assertEquals("employee",            kolayPerson.accessType);
        assertEquals("TR",                  kolayPerson.nationality);
        assertEquals("12345678910",         kolayPerson.idNumber);
        assertEquals("2022-01-01",          kolayPerson.employmentStartDate);
        assertEquals("definite",            kolayPerson.contractType);
        assertEquals("2023-01-01",          kolayPerson.contractEndDate);
        assertEquals("2000-01-01",          kolayPerson.birthday);
        assertEquals("single",              kolayPerson.maritalStatus);
        assertEquals("notWorking",          kolayPerson.partnerWorkStatus);
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
        assertEquals("KolayPerson[" +
                "id=0" +
                " firstName=firstName" +
                " lastName=lastName" +
                " email=sample@email.com" +
                " workEmail=sample@workmail.com" +
                " mobilePhone=+905555555555" +
                " workPhone=+905355353535" +
                " gender=male" +
                " accessType=employee" +
                " nationality=TR" +
                " idNumber=12345678910" +
                " employmentStartDate=2022-01-01" +
                " contractType=definite" +
                " contractEndDate=2023-01-01" +
                " birthday=2000-01-01" +
                " maritalStatus=single" +
                " partnerWorkStatus=notWorking" +
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
        assertNotEquals(kolayPerson, kolayPerson.withId(null));
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
        assertEquals(person, kolayPerson.toPerson());
    }

    @Test
    void whenKolayPersonFieldIsNull_personFieldIsAlsoNull() {
        assertEquals(person.withId(null), kolayPerson.withId(null).toPerson());
        assertEquals(person.withFirstName(null), kolayPerson.withFirstName(null).toPerson());
        assertEquals(person.withLastName(null), kolayPerson.withLastName(null).toPerson());
        assertEquals(person.withEmail(null), kolayPerson.withEmail(null).toPerson());
        assertEquals(person.withWorkEmail(null), kolayPerson.withWorkEmail(null).toPerson());
        assertEquals(person.withMobilePhone(null), kolayPerson.withMobilePhone(null).toPerson());
        assertEquals(person.withWorkPhone(null), kolayPerson.withWorkPhone(null).toPerson());
        assertEquals(person.withGender(null), kolayPerson.withGender(null).toPerson());
        assertEquals(person.withAccessType(null), kolayPerson.withAccessType(null).toPerson());
        assertEquals(person.withNationality(null), kolayPerson.withNationality(null).toPerson());
        assertEquals(person.withIdNumber(null), kolayPerson.withIdNumber(null).toPerson());
        assertEquals(person.withEmploymentStartDate(null), kolayPerson.withEmploymentStartDate(null).toPerson());
        assertEquals(person.withContractEndDate(null), kolayPerson.withContractEndDate(null).toPerson());
        assertEquals(person.withBirthday(null), kolayPerson.withBirthday(null).toPerson());
        assertEquals(person.withMaritalStatus(null), kolayPerson.withMaritalStatus(null).toPerson());
        assertEquals(person.withPartnerWorkStatus(null), kolayPerson.withPartnerWorkStatus(null).toPerson());
        assertEquals(person.withMilitaryService(null), kolayPerson.withMilitaryService(null).toPerson());
        assertEquals(person.withMilitaryServiceDelayDate(null), kolayPerson.withMilitaryServiceDelayDate(null).toPerson());
        assertEquals(person.withEducationLevel(null), kolayPerson.withEducationLevel(null).toPerson());
        assertEquals(person.withGraduated(null), kolayPerson.withEducationStatus(null).toPerson());
        assertEquals(person.withEducationalInstitution(null), kolayPerson.withEducationalInstitution(null).toPerson());
        assertEquals(person.withTourStatus(null), kolayPerson.withTourStatus(null).toPerson());
        assertEquals(person.withLevelOfDisability(null), kolayPerson.withLevelOfDisability(null).toPerson());
        assertEquals(person.withNumberOfChildren(null), kolayPerson.withNumberOfChildren(null).toPerson());
        assertEquals(person.withBloodType(null), kolayPerson.withBloodType(null).toPerson());
    }

    @Test
    void convertsToBloodType() {
        assertEquals(BloodType.ANegative,    kolayPerson.withBloodType("A-").toPerson().bloodType);
        assertEquals(BloodType.APositive,    kolayPerson.withBloodType("A+").toPerson().bloodType);
        assertEquals(BloodType.ZeroNegative, kolayPerson.withBloodType("0-").toPerson().bloodType);
        assertEquals(BloodType.ZeroPositive, kolayPerson.withBloodType("0+").toPerson().bloodType);
        assertEquals(BloodType.ABNegative,   kolayPerson.withBloodType("AB-").toPerson().bloodType);
        assertEquals(BloodType.ABPositive,   kolayPerson.withBloodType("AB+").toPerson().bloodType);
        assertEquals(BloodType.BNegative,    kolayPerson.withBloodType("B-").toPerson().bloodType);
        assertEquals(BloodType.BPositive,    kolayPerson.withBloodType("B+").toPerson().bloodType);
    }

    @Test
    void whenKolayPhoneStringIsInvalid_throwsError() {
        assertThrows(InvalidPhoneError.class, () -> kolayPerson.withMobilePhone("a").toPerson());
        assertThrows(InvalidPhoneError.class, () -> kolayPerson.withMobilePhone("b").toPerson());
        assertThrows(InvalidPhoneError.class, () -> kolayPerson.withMobilePhone("aaaaaaaaaa").toPerson());
        assertThrows(InvalidPhoneError.class, () -> kolayPerson.withMobilePhone("+905555").toPerson());
    }

    @Test
    void whenKolayEnumStringIsInvalid_throwsError() {
        assertThrows(RuntimeException.class, () -> kolayPerson.withPartnerWorkStatus("a").toPerson());
        assertThrows(RuntimeException.class, () -> kolayPerson.withEducationStatus("a").toPerson());
        assertThrows(RuntimeException.class, () -> kolayPerson.withStatus("a").toPerson());
        assertThrows(RuntimeException.class, () -> kolayPerson.withBloodType("a").toPerson());
    }

    @Test
    void convertsFromPerson() {
        assertEquals(clear(kolayPerson), KolayPerson.fromPerson(person));
    }

    @Test
    void whenPersonFieldIsNull_KolayPersonFieldIsAlsoNull() {
        KolayPerson kp = clear(kolayPerson);
        assertEquals(kp.withFirstName(null), KolayPerson.fromPerson(person.withFirstName(null)));
        assertEquals(kp.withLastName(null), KolayPerson.fromPerson(person.withLastName(null)));
        assertEquals(kp.withEmail(null), KolayPerson.fromPerson(person.withEmail(null)));
        assertEquals(kp.withWorkEmail(null), KolayPerson.fromPerson(person.withWorkEmail(null)));
        assertEquals(kp.withMobilePhone(null), KolayPerson.fromPerson(person.withMobilePhone(null)));
        assertEquals(kp.withWorkPhone(null), KolayPerson.fromPerson(person.withWorkPhone(null)));
        assertEquals(kp.withGender(null), KolayPerson.fromPerson(person.withGender(null)));
        assertEquals(kp.withNationality(null), KolayPerson.fromPerson(person.withNationality(null)));
        assertEquals(kp.withIdNumber(null), KolayPerson.fromPerson(person.withIdNumber(null)));
        assertEquals(kp.withEmploymentStartDate(null), KolayPerson.fromPerson(person.withEmploymentStartDate(null)));
        assertEquals(kp.withBirthday(null), KolayPerson.fromPerson(person.withBirthday(null)));
        assertEquals(kp.withMaritalStatus(null), KolayPerson.fromPerson(person.withMaritalStatus(null)));
        assertEquals(kp.withPartnerWorkStatus(null), KolayPerson.fromPerson(person.withPartnerWorkStatus(null)));
        assertEquals(kp.withMilitaryService(null), KolayPerson.fromPerson(person.withMilitaryService(null)));
        assertEquals(kp.withMilitaryServiceDelayDate(null), KolayPerson.fromPerson(person.withMilitaryServiceDelayDate(null)));
        assertEquals(kp.withEducationLevel(null), KolayPerson.fromPerson(person.withEducationLevel(null)));
        assertEquals(kp.withEducationStatus(null), KolayPerson.fromPerson(person.withGraduated(null)));
        assertEquals(kp.withEducationalInstitution(null), KolayPerson.fromPerson(person.withEducationalInstitution(null)));
        assertEquals(kp.withTourStatus(null), KolayPerson.fromPerson(person.withTourStatus(null)));
        assertEquals(kp.withLevelOfDisability(null), KolayPerson.fromPerson(person.withLevelOfDisability(null)));
        assertEquals(kp.withNumberOfChildren(null), KolayPerson.fromPerson(person.withNumberOfChildren(null)));
        assertEquals(kp.withBloodType(null), KolayPerson.fromPerson(person.withBloodType(null)));
    }

    @Test
    void convertsToKolayBloodType() {
        assertEquals("A-",    KolayPerson.fromPerson(person.withBloodType(BloodType.ANegative)).bloodType);
        assertEquals("A+",    KolayPerson.fromPerson(person.withBloodType(BloodType.APositive)).bloodType);
        assertEquals("0-",    KolayPerson.fromPerson(person.withBloodType(BloodType.ZeroNegative)).bloodType);
        assertEquals("0+",    KolayPerson.fromPerson(person.withBloodType(BloodType.ZeroPositive)).bloodType);
        assertEquals("AB-",   KolayPerson.fromPerson(person.withBloodType(BloodType.ABNegative)).bloodType);
        assertEquals("AB+",   KolayPerson.fromPerson(person.withBloodType(BloodType.ABPositive)).bloodType);
        assertEquals("B-",    KolayPerson.fromPerson(person.withBloodType(BloodType.BNegative)).bloodType);
        assertEquals("B+",    KolayPerson.fromPerson(person.withBloodType(BloodType.BPositive)).bloodType);
    }

    @Test
    void whenPersonContractEndDateIsNull_kolayPersonContractTypeIsIndefinite() {
        Person person = this.person.withContractEndDate(null);
        KolayPerson expected = clear(this.kolayPerson).withContractType("indefinite").withContractEndDate(null);
        assertEquals(expected, KolayPerson.fromPerson(person));
    }

    @Test
    public void convertsToJson() {
        assertEquals(mapper.valueToTree(kolayPerson), kolayPerson.toJson());
    }

    @Test
    public void convertsFromJson() {
        assertEquals(kolayPerson, KolayPerson.fromJson(mapper.valueToTree(kolayPerson)));
    }

    private KolayPerson clear(KolayPerson p) {
        return p.withCreatedAt(null).withUpdatedAt(null);
    }
}
