package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.sadesoft.portal.base.repo.ListRepo;
import com.sadesoft.portal.core.model.*;
import com.sadesoft.portal.core.sample.PersonSamples;

public class PersonServiceTest {
    private ListRepo<Person> repo;
    private PersonService service;
    private Person validPerson;

    private void assertMissingField(Person person) {
        assertThrows(MissingFieldError.class, () -> service.create(person));
    }

    private void assertEmptyField(Person person, String fieldName) {
        EmptyFieldError e = assertThrows(EmptyFieldError.class, () -> service.create(person));
        assertEquals("Person '" + fieldName + "' field is empty.", e.getMessage());
    }

    private void assertInvalidName(Person person, String fieldName, int index, char c) {
        InvalidCharacterError e = assertThrows(InvalidCharacterError.class, () -> service.create(person));
        assertEquals(fieldName + " has invalid char '" + c + "' at index: " + index + ".", e.getMessage());
    }

    private void assertInvalidEmail(Person person) {
        InvalidEmailError e = assertThrows(InvalidEmailError.class, () -> service.create(person));
        assertEquals("Invalid email '" + person.email + "' in field 'email'.", e.getMessage());
    }

    private void assertInvalidWorkEmail(Person person) {
        InvalidEmailError e = assertThrows(InvalidEmailError.class, () -> service.create(person));
        assertEquals("Invalid email '" + person.workEmail + "' in field 'workEmail'.", e.getMessage());
    }

    private void assertCopiedField(Person person) {
        CopiedFieldError e = assertThrows(CopiedFieldError.class, () -> service.create(person));
        assertEquals("Fields '" + person.email + "' and '" + person.workEmail + "' should not be the same.", e.getMessage());
    }

    private void assertInvalidMobilePhone(Person person) {
        InvalidPhoneError e = assertThrows(InvalidPhoneError.class, () -> service.create(person));
        assertEquals("Invalid phone number '" + person.mobilePhone + "' in field 'mobilePhone'.", e.getMessage());
    }

    private void assertInvalidWorkPhone(Person person) {
        InvalidPhoneError e = assertThrows(InvalidPhoneError.class, () -> service.create(person));
        assertEquals("Invalid phone number '" + person.workPhone + "' in field 'workPhone'.", e.getMessage());
    }

    @BeforeEach
    public void setup() {
        repo = new ListRepo<>("Person");
        service = new PersonService(repo);
        validPerson = PersonSamples.validPerson;
    }

    @Test
    public void createsPerson() {
        Person person = service.create(validPerson);
        assertEquals(validPerson.withId(0), person);
        assertEquals(1, repo.list().size());
        assertEquals(person, repo.read(0));
    }

    @Test
    public void whenHasMissingFieldOnCreate_throwsError() {
        assertMissingField(validPerson.withFirstName(null));
        assertMissingField(validPerson.withLastName(null));
        assertMissingField(validPerson.withEmail(null));
        assertMissingField(validPerson.withWorkEmail(null));
        assertMissingField(validPerson.withMobilePhone(null));
        assertMissingField(validPerson.withWorkPhone(null));
        assertMissingField(validPerson.withEmploymentStartDate(null));
        assertMissingField(validPerson.withContractType(null));
        assertMissingField(validPerson.withContractEndDate(null));
        assertMissingField(validPerson.withWorkingDayId(null));
        assertMissingField(validPerson.withAccessType(null));
        assertMissingField(validPerson.withBirthday(null));
        assertMissingField(validPerson.withBloodType(null));
        assertMissingField(validPerson.withEducationalLevel(null));
        assertMissingField(validPerson.withEducationStatus(null));
        assertMissingField(validPerson.withEducationalInstitution(null));
        assertMissingField(validPerson.withGender(null));
        assertMissingField(validPerson.withIdNumber(null));
        assertMissingField(validPerson.withLevelOfDisability(null));
        assertMissingField(validPerson.withMaritalStatus(null));
        assertMissingField(validPerson.withPartnerWorkStatus(null));
        assertMissingField(validPerson.withMilitaryService(null));
        assertMissingField(validPerson.withMilitaryServiceDelayDate(null));
        assertMissingField(validPerson.withNationality(null));
        assertMissingField(validPerson.withNumberOfChildren(null));
        assertMissingField(validPerson.withTourStatus(null));
    }

    @Test
    public void whenFirstNameIsEmpty_throwsError() {
        assertEmptyField(validPerson.withFirstName(""), "firstName");
    }

    @Test
    public void whenLastNameIsEmpty_throwsError() {
        assertEmptyField(validPerson.withLastName(""), "lastName");
    }

    @Test
    public void whenFirstNameContainsNumber_throwsError() {
        assertInvalidName(validPerson.withFirstName("1"), "firstName", 0, '1');
        assertInvalidName(validPerson.withFirstName("2"), "firstName", 0, '2');
        assertInvalidName(validPerson.withFirstName("first1Name"), "firstName", 5, '1');
        assertInvalidName(validPerson.withFirstName("'"), "firstName", 0, '\'');
    }

    @Test
    public void whenLastNameContainsNumber_throwsError() {
        assertInvalidName(validPerson.withLastName("1"), "lastName", 0, '1');
    }

    @Test
    public void whenEmailIsInvalid_throwsError() {
        assertInvalidEmail(validPerson.withEmail("s"));
        assertInvalidEmail(validPerson.withEmail("x"));
        assertInvalidEmail(validPerson.withEmail("ab"));
        assertInvalidEmail(validPerson.withEmail("@"));
        assertInvalidEmail(validPerson.withEmail("a@"));
    }

    @Test
    public void whenWorkEmailIsInvalid_throwsError() {
        assertInvalidWorkEmail(validPerson.withWorkEmail("a"));
    }

    @Test
    public void whenEmailAndWorkEmailAreTheSame_throwsError() {
        assertCopiedField(validPerson.withWorkEmail(validPerson.email));
    }

    @Test
    public void whenPhoneNumberIsInvalid_throwsError() {
        assertInvalidMobilePhone(validPerson.withMobilePhone("a"));
        assertInvalidEmail(validPerson.withEmail("x"));
        assertInvalidEmail(validPerson.withEmail("+9055555555555"));
        assertInvalidEmail(validPerson.withEmail("+90 "));
        assertInvalidEmail(validPerson.withEmail("90 5555555555"));
    }

    @Test
    public void whenWorkNumberIsInvalid_throwsError() {
        assertInvalidWorkPhone(validPerson.withWorkPhone("a"));
    }

    @Nested
    public class GivenServiceWithOnePerson {
        @BeforeEach
        public void setup() {
            service.create(validPerson);
        }

        @Test
        public void listsPeople() {
            assertEquals(1, service.list().size());
            assertEquals(validPerson.withId(0), service.list().get(0));
            assertEquals(validPerson.withId(0), service.read(0));
        }

        @Test
        public void updatesPersonById() {
            Person person = service.update(0, validPerson.withFirstName("updated"));
            assertEquals(validPerson.withId(0).withFirstName("updated"), person);
            assertEquals(person, repo.read(0));
        }

        @Test
        public void deletesPersonById() {
            Person person = service.delete(0);
            assertEquals(validPerson.withId(0), person);
            assertEquals(0, service.list().size());
        }

        @Test
        public void whenHasMissingFieldOnUpdate_throwsError(){
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withFirstName(null)));
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withLastName(null)));
        }
    }
}
