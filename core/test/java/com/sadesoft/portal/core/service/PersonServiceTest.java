package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.*;
import com.sadesoft.portal.core.mock.MockPersonRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.sadesoft.portal.core.model.*;
import com.sadesoft.portal.core.sample.PersonSamples;

public class PersonServiceTest {
    private MockPersonRepo repo;
    private PersonService service;
    private Person validPerson;

    private String createString(int n, String s) {
        return new String(new char[n]).replace("\0", s);
    }

    private void assertValid(Person person) {
        assertDoesNotThrow(() -> service.create(person));
    }

    private void assertMissingField(Person person) {
        assertThrows(MissingFieldError.class, () -> service.create(person));
    }

    private void assertEmptyField(Person person, String field) {
        EmptyFieldError e = assertThrows(EmptyFieldError.class, () -> service.create(person));
        assertEquals("Field '" + field + "' should not be empty.", e.getMessage());
    }

    private void assertInvalidCharacter(Person person, String field, int index, char c) {
        InvalidCharacterError e = assertThrows(InvalidCharacterError.class, () -> service.create(person));
        assertEquals("Field '" + field + "' has invalid char '" + c + "' at index: " + index + ".", e.getMessage());
    }

    private void assertInvalidEmail(Person person) {
        InvalidEmailError e = assertThrows(InvalidEmailError.class, () -> service.create(person));
        assertEquals("Invalid email '" + person.email + "' in field 'email'.", e.getMessage());
    }

    private void assertInvalidWorkEmail(Person person) {
        InvalidEmailError e = assertThrows(InvalidEmailError.class, () -> service.create(person));
        assertEquals("Invalid email '" + person.workEmail + "' in field 'workEmail'.", e.getMessage());
    }

    private void assertCopiedField(Person person, String field1, String field2) {
        CopiedFieldError e = assertThrows(CopiedFieldError.class, () -> service.create(person));
        assertEquals("Fields '" + field1 + "' and '" + field2 + "' should not be the same.", e.getMessage());
    }

    private void assertInvalidMobilePhone(Person person) {
        InvalidPhoneError e = assertThrows(InvalidPhoneError.class, () -> service.create(person));
        assertEquals("Invalid phone number '" + person.mobilePhone + "' in field 'mobilePhone'.", e.getMessage());
    }

    private void assertInvalidWorkPhone(Person person) {
        InvalidPhoneError e = assertThrows(InvalidPhoneError.class, () -> service.create(person));
        assertEquals("Invalid phone number '" + person.workPhone + "' in field 'workPhone'.", e.getMessage());
    }

    private void assertInvalidEducationalInstitution(Person person) {
        InvalidLengthError e = assertThrows(InvalidLengthError.class, () -> service.create(person));
        assertEquals("Invalid length '" + person.educationalInstitution.length() +
                "' in field 'educationalInstitution'.", e.getMessage());
    }

    private void assertInvalidNationality(Person person) {
        InvalidLengthError e = assertThrows(InvalidLengthError.class, () -> service.create(person));
        assertEquals("Invalid length '" + person.nationality.length() + "' in field 'nationality'.", e.getMessage());
    }

    private void assertInvalidIdNumber(Person person) {
        InvalidLengthError e = assertThrows(InvalidLengthError.class, () -> service.create(person));
        assertEquals("Invalid length '" + person.idNumber.length() + "' in field 'idNumber'.", e.getMessage());
    }

    @BeforeEach
    public void setup() {
        repo = new MockPersonRepo();
        service = new PersonService(repo);
        validPerson = PersonSamples.validPerson.copy();
    }

    @Test
    public void createsPerson() {
        Person person = service.create(validPerson);
        assertEquals(validPerson.withId("0"), person);
        assertEquals(1, repo.list().size());
        assertEquals(person, repo.read("0"));
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
        assertValid(validPerson.withContractEndDate(null));
        assertMissingField(validPerson.withAccessType(null));
        assertMissingField(validPerson.withBirthday(null));
        assertMissingField(validPerson.withBloodType(null));
        assertMissingField(validPerson.withEducationLevel(null));
        assertMissingField(validPerson.withGraduated(null));
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
        assertInvalidCharacter(validPerson.withFirstName("1"), "firstName", 0, '1');
        assertInvalidCharacter(validPerson.withFirstName("2"), "firstName", 0, '2');
        assertInvalidCharacter(validPerson.withFirstName("first1Name"), "firstName", 5, '1');
        assertInvalidCharacter(validPerson.withFirstName("'"), "firstName", 0, '\'');
    }

    @Test
    public void whenLastNameContainsNumber_throwsError() {
        assertInvalidCharacter(validPerson.withLastName("1"), "lastName", 0, '1');
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
        assertCopiedField(validPerson.withWorkEmail(validPerson.email), "email", "workEmail");
    }

    @Test
    public void whenPhoneNumberIsInvalid_throwsError() {
        assertInvalidMobilePhone(validPerson.withMobilePhone("a"));
        assertInvalidMobilePhone(validPerson.withMobilePhone("x"));
        assertInvalidMobilePhone(validPerson.withMobilePhone("+9055555555555"));
        assertInvalidMobilePhone(validPerson.withMobilePhone("+90 "));
        assertInvalidMobilePhone(validPerson.withMobilePhone("90 5555555555"));
        assertInvalidMobilePhone(validPerson.withMobilePhone(""));
    }

    @Test
    public void whenWorkNumberIsInvalid_throwsError() {
        assertInvalidWorkPhone(validPerson.withWorkPhone("a"));
        assertValid(validPerson.withWorkPhone(""));
    }

    @Test
    public void whenEducationalInstitutionLengthISInvalid_throwsError() {
        assertInvalidEducationalInstitution(validPerson.withEducationalInstitution(createString(151, "a")));
        assertValid(validPerson.withEducationalInstitution(createString(150, "a")));
    }

    @Test
    public void whenNationalityIsInvalid_throwsError() {
        assertInvalidNationality(validPerson.withNationality("A"));
        assertValid(validPerson.withNationality("AA"));
        assertInvalidNationality(validPerson.withNationality("AAA"));
    }

    @Test
    public void whenIdNumberIsInvalid_throwsError() {
        assertValid(validPerson.withNationality("AA").withIdNumber("1"));
        assertInvalidIdNumber(validPerson.withNationality("TR").withIdNumber("1"));
        assertInvalidIdNumber(validPerson.withNationality("TR").withIdNumber("1111111111"));
        assertValid(validPerson.withNationality("TR").withIdNumber("11111111110"));
        assertInvalidIdNumber(validPerson.withNationality("TR").withIdNumber("111111111111"));
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
            assertEquals(validPerson.withId("0"), service.list().get(0));
            assertEquals(validPerson.withId("0"), service.read("0"));
        }

        @Test
        public void updatesPersonById() {
            Person person = service.update("0", validPerson.withFirstName("updated"));
            assertEquals(validPerson.withId("0").withFirstName("updated"), person);
            assertEquals(person, repo.read("0"));
        }

        @Test
        public void deletesPersonById() {
            Person person = service.delete("0");
            assertEquals(validPerson.withId("0"), person);
            assertEquals(0, service.list().size());
        }

        @Test
        public void whenHasMissingFieldOnUpdate_throwsError(){
            assertThrows(MissingFieldError.class, () -> service.update("0", validPerson.withFirstName(null)));
            assertThrows(MissingFieldError.class, () -> service.update("0", validPerson.withLastName(null)));
        }
    }
}
