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
        assertMissingFieldOnCreate(validPerson.withFirstName(null));
        assertMissingFieldOnCreate(validPerson.withLastName(null));
        assertMissingFieldOnCreate(validPerson.withEmail(null));
        assertMissingFieldOnCreate(validPerson.withWorkEmail(null));
        assertMissingFieldOnCreate(validPerson.withMobilePhone(null));
        assertMissingFieldOnCreate(validPerson.withWorkPhone(null));
        assertMissingFieldOnCreate(validPerson.withEmploymentStartDate(null));
        assertMissingFieldOnCreate(validPerson.withContractType(null));
        assertMissingFieldOnCreate(validPerson.withContractEndDate(null));
        assertMissingFieldOnCreate(validPerson.withWorkingDayId(null));
        assertMissingFieldOnCreate(validPerson.withAccessType(null));
        assertMissingFieldOnCreate(validPerson.withBirthday(null));
        assertMissingFieldOnCreate(validPerson.withBloodType(null));
        assertMissingFieldOnCreate(validPerson.withEducationalLevel(null));
        assertMissingFieldOnCreate(validPerson.withEducationStatus(null));
        assertMissingFieldOnCreate(validPerson.withEducationalInstitution(null));
        assertMissingFieldOnCreate(validPerson.withGender(null));
        assertMissingFieldOnCreate(validPerson.withIdNumber(null));
        assertMissingFieldOnCreate(validPerson.withLevelOfDisability(null));
        assertMissingFieldOnCreate(validPerson.withMaritalStatus(null));
        assertMissingFieldOnCreate(validPerson.withPartnerWorkStatus(null));
        assertMissingFieldOnCreate(validPerson.withMilitaryService(null));
        assertMissingFieldOnCreate(validPerson.withMilitaryServiceDelayDate(null));
        assertMissingFieldOnCreate(validPerson.withNationality(null));
        assertMissingFieldOnCreate(validPerson.withNumberOfChildren(null));
        assertMissingFieldOnCreate(validPerson.withTourStatus(null));
    }

    private void assertMissingFieldOnCreate(Person person) {
        assertThrows(MissingFieldError.class, () -> service.create(person));
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
            assertMissingFieldOnUpdate(validPerson.withFirstName(null));
            assertMissingFieldOnUpdate(validPerson.withLastName(null));
            assertMissingFieldOnUpdate(validPerson.withEmail(null));
            assertMissingFieldOnUpdate(validPerson.withWorkEmail(null));
            assertMissingFieldOnUpdate(validPerson.withMobilePhone(null));
            assertMissingFieldOnUpdate(validPerson.withWorkPhone(null));
            assertMissingFieldOnUpdate(validPerson.withEmploymentStartDate(null));
            assertMissingFieldOnUpdate(validPerson.withContractType(null));
            assertMissingFieldOnUpdate(validPerson.withContractEndDate(null));
            assertMissingFieldOnUpdate(validPerson.withWorkingDayId(null));
            assertMissingFieldOnUpdate(validPerson.withAccessType(null));
            assertMissingFieldOnUpdate(validPerson.withBirthday(null));
            assertMissingFieldOnUpdate(validPerson.withBloodType(null));
            assertMissingFieldOnUpdate(validPerson.withEducationalLevel(null));
            assertMissingFieldOnUpdate(validPerson.withEducationStatus(null));
            assertMissingFieldOnUpdate(validPerson.withEducationalInstitution(null));
            assertMissingFieldOnUpdate(validPerson.withGender(null));
            assertMissingFieldOnUpdate(validPerson.withIdNumber(null));
            assertMissingFieldOnUpdate(validPerson.withLevelOfDisability(null));
            assertMissingFieldOnUpdate(validPerson.withMaritalStatus(null));
            assertMissingFieldOnUpdate(validPerson.withPartnerWorkStatus(null));
            assertMissingFieldOnUpdate(validPerson.withMilitaryService(null));
            assertMissingFieldOnUpdate(validPerson.withMilitaryServiceDelayDate(null));
            assertMissingFieldOnUpdate(validPerson.withNationality(null));
            assertMissingFieldOnUpdate(validPerson.withNumberOfChildren(null));
            assertMissingFieldOnUpdate(validPerson.withTourStatus(null));
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
        public void whenPhoneNumberIsInvalid_throwsError() {
            assertInvalidMobilePhone(validPerson.withMobilePhone("a"));
        }

        @Test
        public void whenWorkNumberIsInvalid_throwsError() {
            assertInvalidWorkPhone(validPerson.withWorkPhone("a"));
        }

        private void assertMissingFieldOnUpdate(Person person) {
            assertThrows(MissingFieldError.class, () -> service.update(0, person));
        }

        private void assertEmptyField(Person person, String fieldName) {
            var error = assertThrows(EmptyFieldError.class, () -> service.create(person));
            assertEquals("Person '" + fieldName + "' field is empty.", error.getMessage());
        }

        private void assertInvalidName(Person person, String fieldName, int index, char c) {
            var error = assertThrows(InvalidCharacterError.class, () -> service.create(person));
            assertEquals(fieldName + " has invalid char '" + c + "' at index: " + index + ".", error.getMessage());
        }

        private void assertInvalidEmail(Person person) {
            var error = assertThrows(InvalidEmailError.class, () -> service.create(person));
            assertEquals("Invalid email '" + person.email + "' in field 'email'.", error.getMessage());
        }

        private void assertInvalidWorkEmail(Person person) {
            var error = assertThrows(InvalidEmailError.class, () -> service.create(person));
            assertEquals("Invalid email '" + person.workEmail + "' in field 'workEmail'.", error.getMessage());
        }

        private void assertInvalidMobilePhone(Person person) {
            var error = assertThrows(InvalidPhoneError.class, () -> service.create(person));
            assertEquals("Invalid phone number '" + person.mobilePhone + "' in field 'mobilePhone'.", error.getMessage());
        }

        private void assertInvalidWorkPhone(Person person) {
            var error = assertThrows(InvalidPhoneError.class, () -> service.create(person));
            assertEquals("Invalid phone number '" + person.workPhone + "' in field 'workPhone'.", error.getMessage());
        }
    }
}
