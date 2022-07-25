package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.sadesoft.portal.base.repo.ListRepo;
import java.time.LocalDate;

public class PersonServiceTest {
    ListRepo<Person> repo;
    PersonService service;
    Person validPerson;

    @BeforeEach
    public void setup() {
        repo = new ListRepo<>("Person");
        service = new PersonService(repo);
        validPerson = new Person(0, "firstName", "lastName", "fl@email.com",
                "fl@workmail.com", "05555555555", "05355353535", LocalDate.of(2022,1,1),
                ContractType.Definite , LocalDate.of(2023,1,1), "123", AccessType.Employee,
                LocalDate.of(2000,1,1), BloodType.APositive,
                EducationalLevel.Bachelor, EducationStatus.Student, "YTU",
                Gender.Male, "12345678910", LevelOfDisability.NotDisabled,
                MaritalStatus.Single, false, MilitaryService.Uncompleted,
                LocalDate.of(2024,1,1), "TR", 0, TourStatus.Completed);
    }

    @Test
    public void createsPerson() {
        Person person = service.create(validPerson);
        assertEquals(1, repo.list().size());
        assertEquals(0, person.id);
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
    public void whenCreatesPerson_validatesAllFieldsExist() {
        assertThrows(MissingFieldError.class, () -> service.create(validPerson.withFirstName(null)));
        assertThrows(MissingFieldError.class, () -> service.create(validPerson.withLastName(null)));
        assertThrows(MissingFieldError.class, () -> service.create(validPerson.withEmail(null)));
        assertThrows(MissingFieldError.class, () -> service.create(validPerson.withWorkEmail(null)));
        assertThrows(MissingFieldError.class, () -> service.create(validPerson.withMobilePhone(null)));
        assertThrows(MissingFieldError.class, () -> service.create(validPerson.withWorkPhone(null)));
        assertThrows(MissingFieldError.class, () -> service.create(validPerson.withEmploymentStartDate(null)));
        assertThrows(MissingFieldError.class, () -> service.create(validPerson.withContractType(null)));
        assertThrows(MissingFieldError.class, () -> service.create(validPerson.withContractEndDate(null)));
        assertThrows(MissingFieldError.class, () -> service.create(validPerson.withWorkingDayId(null)));
        assertThrows(MissingFieldError.class, () -> service.create(validPerson.withAccessType(null)));
        assertThrows(MissingFieldError.class, () -> service.create(validPerson.withBirthday(null)));
        assertThrows(MissingFieldError.class, () -> service.create(validPerson.withBloodType(null)));
        assertThrows(MissingFieldError.class, () -> service.create(validPerson.withEducationalLevel(null)));
        assertThrows(MissingFieldError.class, () -> service.create(validPerson.withEducationStatus(null)));
        assertThrows(MissingFieldError.class, () -> service.create(validPerson.withEducationalInstitution(null)));
        assertThrows(MissingFieldError.class, () -> service.create(validPerson.withGender(null)));
        assertThrows(MissingFieldError.class, () -> service.create(validPerson.withIdNumber(null)));
        assertThrows(MissingFieldError.class, () -> service.create(validPerson.withLevelOfDisability(null)));
        assertThrows(MissingFieldError.class, () -> service.create(validPerson.withMaritalStatus(null)));
        assertThrows(MissingFieldError.class, () -> service.create(validPerson.withPartnerWorkStatus(null)));
        assertThrows(MissingFieldError.class, () -> service.create(validPerson.withMilitaryService(null)));
        assertThrows(MissingFieldError.class, () -> service.create(validPerson.withMilitaryServiceDelayDate(null)));
        assertThrows(MissingFieldError.class, () -> service.create(validPerson.withNationality(null)));
        assertThrows(MissingFieldError.class, () -> service.create(validPerson.withNumberOfChildren(null)));
        assertThrows(MissingFieldError.class, () -> service.create(validPerson.withTourStatus(null)));
    }

    @Nested
    public class GivenServiceWithOnePerson {
        @BeforeEach
        public void setup() {
            repo.create(new Person("firstName", "lastName", "fl@email.com",
                    "fl@workmail.com", "05555555555", "05355353535", LocalDate.of(2022, 1, 1),
                    ContractType.Definite, LocalDate.of(2023, 1, 1), "123", AccessType.Employee,
                    LocalDate.of(2000, 1, 1), BloodType.APositive,
                    EducationalLevel.Bachelor, EducationStatus.Student, "YTU",
                    Gender.Male, "12345678910", LevelOfDisability.NotDisabled,
                    MaritalStatus.Single, false, MilitaryService.Uncompleted,
                    LocalDate.of(2024, 1, 1), "TR", 0, TourStatus.Completed));
        }

        @Test
        public void listPeople() {
            assertEquals(1, service.list().size());
            assertEquals("firstName", service.list().get(0).firstName);
        }

        @Test
        public void readsPersonById() {
            assertEquals("firstName", service.read(0).firstName);
        }

        @Test
        public void updatesPersonById() {
            Person person = service.update(0, new Person("updated", "lastName", "fl@email.com",
                    "fl@workmail.com", "05555555555", "05355353535", LocalDate.of(2022,1,1),
                    ContractType.Definite , LocalDate.of(2023,1,1), "123", AccessType.Employee,
                    LocalDate.of(2000,1,1), BloodType.APositive,
                    EducationalLevel.Bachelor, EducationStatus.Student, "YTU",
                    Gender.Male, "12345678910", LevelOfDisability.NotDisabled,
                    MaritalStatus.Single, false, MilitaryService.Uncompleted,
                    LocalDate.of(2024,1,1), "TR", 0, TourStatus.Completed));
            assertEquals("updated", person.firstName);
            assertEquals("updated", repo.read(0).firstName);
        }

        @Test
        public void deletesUserById() {
            Person person = service.delete(0);
            assertEquals(0, repo.list().size());
            assertEquals(0, person.id);
        }

        @Test
        public void whenUpdatesPersonWithoutNecessaryInformation_throwError(){
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withFirstName(null)));
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withLastName(null)));
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withEmail(null)));
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withWorkEmail(null)));
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withMobilePhone(null)));
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withWorkPhone(null)));
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withEmploymentStartDate(null)));
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withContractType(null)));
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withContractEndDate(null)));
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withWorkingDayId(null)));
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withAccessType(null)));
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withBirthday(null)));
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withBloodType(null)));
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withEducationalLevel(null)));
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withEducationStatus(null)));
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withEducationalInstitution(null)));
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withGender(null)));
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withIdNumber(null)));
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withLevelOfDisability(null)));
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withMaritalStatus(null)));
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withPartnerWorkStatus(null)));
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withMilitaryService(null)));
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withMilitaryServiceDelayDate(null)));
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withNationality(null)));
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withNumberOfChildren(null)));
            assertThrows(MissingFieldError.class, () -> service.update(0, validPerson.withTourStatus(null)));
        }
    }
}
