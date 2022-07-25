package com.sadesoft.portal.core.service;

import java.time.LocalDate;
import java.util.List;

import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.model.*;

public class PersonService {
    private final Repo<Person> repo;

    public PersonService(Repo<Person> repo) {
        this.repo = repo;
    }

    public List<Person> list() {
        return repo.list();
    }

    public Person read(int id) {
        return repo.read(id);
    }

    public Person create(Person person) {
        validateFields(person);
        return repo.create(person);
    }

    public Person update(int id, Person person) {
        validateFields(person);
        return repo.update(id, person);
    }

    public Person delete(int id) {
        return repo.delete(id);
    }

    private void validateFields(Person person) {
        validateExists(person.firstName, "firstName");
        validateExists(person.lastName, "lastName");
        validateExists(person.email, "email");
        validateExists(person.workEmail, "workEmail");
        validateExists(person.mobilePhone, "mobilePhone");
        validateExists(person.workPhone, "workPhone");
        validateExists(person.employmentStartDate, String.valueOf(LocalDate.of(2022,1,1)));
        validateExists(person.contractType, "contractType");
        validateExists(person.contractEndDate, String.valueOf(LocalDate.of(2023,1,1)));
        validateExists(person.workingDayId, "workingDayId");
        validateExists(person.accessType, "accessType");
        validateExists(person.birthday, String.valueOf(LocalDate.of(2000,1,1)));
        validateExists(person.bloodType, "bloodType");
        validateExists(person.educationalLevel, "educationalLevel");
        validateExists(person.educationStatus, "educationStatus");
        validateExists(person.educationalInstitution, "educationalInstitution");
        validateExists(person.gender, "gender");
        validateExists(person.idNumber, "12345678910");
        validateExists(person.levelOfDisability, "levelOfDisability");
        validateExists(person.maritalStatus, "Single");
        validateExists(person.partnerWorkStatus, "true");
        validateExists(person.militaryService, "militaryService");
        validateExists(person.militaryServiceDelayDate, String.valueOf(LocalDate.of(2024,1,1)));
        validateExists(person.nationality, "nationality");
        validateExists(person.numberOfChildren, "0");
        validateExists(person.tourStatus, "tourStatus");
    }

    private void validateExists(Object fieldValue, String fieldName) {
        if (fieldValue == null) throw new MissingFieldError("Person", fieldName);
    }
}
