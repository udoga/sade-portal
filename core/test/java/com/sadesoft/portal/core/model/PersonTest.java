package com.sadesoft.portal.core.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.sadesoft.portal.core.sample.PersonSamples;

public class PersonTest {
    private Person validPerson;

    @BeforeEach
    public void setup() {
        validPerson = PersonSamples.validPerson;
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
    public void printsFields() {
        assertEquals("Person[id=null" +
                " firstName=firstName" +
                " lastName=lastName" +
                " email=sample@email.com" +
                " workEmail=sample@workmail.com" +
                " mobilePhone=+90 5555555555" +
                " workPhone=+90 5355353535" +
                " employmentStartDate=2022-01-01" +
                " contractType=Definite" +
                " contractEndDate=2023-01-01" +
                " workingDayId=123" +
                " accessType=Employee" +
                " birthday=2000-01-01" +
                " bloodType=APositive" +
                " educationalLevel=Bachelor" +
                " educationStatus=Student" +
                " educationalInstitution=YTU" +
                " gender=Male" +
                " idNumber=12345678910" +
                " levelOfDisability=NotDisabled" +
                " maritalStatus=Single" +
                " partnerWorkStatus=false" +
                " militaryService=Uncompleted" +
                " militaryServiceDelayDate=2024-01-01" +
                " nationality=TR" +
                " numberOfChildren=0" +
                " tourStatus=Completed" +
                "]", validPerson.toString());
    }

    @Test
    public void clonesWithFields() {
        assertNull(validPerson.withId(null).id);
        assertNull(validPerson.withFirstName(null).firstName);
        assertNull(validPerson.withLastName(null).lastName);
        assertNull(validPerson.withEmail(null).email);
        assertNull(validPerson.withWorkEmail(null).workEmail);
        assertNull(validPerson.withMobilePhone(null).mobilePhone);
        assertNull(validPerson.withWorkPhone(null).workPhone);
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

    @Test
    public void checksEquality() {
        assertEquals(validPerson, validPerson.copy());
        assertNotEquals(validPerson, validPerson.withId(0));
        assertNotEquals(validPerson, validPerson.withFirstName(null));
        assertNotEquals(validPerson, validPerson.withLastName(null));
        assertNotEquals(validPerson, validPerson.withEmail(null));
        assertNotEquals(validPerson, validPerson.withWorkEmail(null));
        assertNotEquals(validPerson, validPerson.withMobilePhone(null));
        assertNotEquals(validPerson, validPerson.withWorkPhone(null));
        assertNotEquals(validPerson, validPerson.withEmploymentStartDate(null));
        assertNotEquals(validPerson, validPerson.withContractType(null));
        assertNotEquals(validPerson, validPerson.withContractEndDate(null));
        assertNotEquals(validPerson, validPerson.withWorkingDayId(null));
        assertNotEquals(validPerson, validPerson.withAccessType(null));
        assertNotEquals(validPerson, validPerson.withBirthday(null));
        assertNotEquals(validPerson, validPerson.withBloodType(null));
        assertNotEquals(validPerson, validPerson.withEducationalLevel(null));
        assertNotEquals(validPerson, validPerson.withEducationStatus(null));
        assertNotEquals(validPerson, validPerson.withEducationalInstitution(null));
        assertNotEquals(validPerson, validPerson.withGender(null));
        assertNotEquals(validPerson, validPerson.withIdNumber(null));
        assertNotEquals(validPerson, validPerson.withLevelOfDisability(null));
        assertNotEquals(validPerson, validPerson.withMaritalStatus(null));
        assertNotEquals(validPerson, validPerson.withPartnerWorkStatus(null));
        assertNotEquals(validPerson, validPerson.withMilitaryService(null));
        assertNotEquals(validPerson, validPerson.withMilitaryServiceDelayDate(null));
        assertNotEquals(validPerson, validPerson.withNationality(null));
        assertNotEquals(validPerson, validPerson.withNumberOfChildren(null));
        assertNotEquals(validPerson, validPerson.withTourStatus(null));
    }
}
