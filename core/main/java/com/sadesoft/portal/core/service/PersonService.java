package com.sadesoft.portal.core.service;

import java.util.List;

import com.sadesoft.portal.base.error.EmptyFieldError;
import com.sadesoft.portal.base.error.InvalidCharacterError;
import com.sadesoft.portal.base.error.InvalidEmailError;
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
        check(person);
        return repo.create(person);
    }

    public Person update(int id, Person person) {
        check(person);
        return repo.update(id, person);
    }

    public Person delete(int id) {
        return repo.delete(id);
    }

    private void check(Person person) {
        checkMissingFields(person);
        checkEmptyFields(person);
        checkInvalidCharacter(person);
        checkInvalidEmail(person);
    }

    private void checkMissingFields(Person person) {
        checkMissingField(person.firstName, "firstName");
        checkMissingField(person.lastName, "lastName");
        checkMissingField(person.email, "email");
        checkMissingField(person.workEmail, "workEmail");
        checkMissingField(person.mobilePhone, "mobilePhone");
        checkMissingField(person.workPhone, "workPhone");
        checkMissingField(person.employmentStartDate, "employmentStartDate");
        checkMissingField(person.contractType, "contractType");
        checkMissingField(person.contractEndDate, "contractEndDate");
        checkMissingField(person.workingDayId, "workingDayId");
        checkMissingField(person.accessType, "accessType");
        checkMissingField(person.birthday, "birthday");
        checkMissingField(person.bloodType, "bloodType");
        checkMissingField(person.educationalLevel, "educationalLevel");
        checkMissingField(person.educationStatus, "educationStatus");
        checkMissingField(person.educationalInstitution, "educationalInstitution");
        checkMissingField(person.gender, "gender");
        checkMissingField(person.idNumber, "idNumber");
        checkMissingField(person.levelOfDisability, "levelOfDisability");
        checkMissingField(person.maritalStatus, "maritalStatus");
        checkMissingField(person.partnerWorkStatus, "partnerWorkStatus");
        checkMissingField(person.militaryService, "militaryService");
        checkMissingField(person.militaryServiceDelayDate, "militaryServiceDelayDate");
        checkMissingField(person.nationality, "nationality");
        checkMissingField(person.numberOfChildren, "numberOfChildren");
        checkMissingField(person.tourStatus, "tourStatus");
    }

    private void checkEmptyFields(Person person) {
        checkEmptyField(person.firstName, "firstName");
        checkEmptyField(person.lastName, "lastName");
    }

    private void checkInvalidCharacter(Person person) {
        checkInvalidCharacter(person.firstName, "firstName");
        checkInvalidCharacter(person.lastName, "lastName");
    }

    private void checkInvalidEmail(Person person) {
        checkInvalidEmail(person.email, "email");
        checkInvalidEmail(person.workEmail, "workEmail");
    }

    private void checkMissingField(Object fieldValue, String fieldName) {
        if (fieldValue == null) throw new MissingFieldError("Person", fieldName);
    }

    private void checkEmptyField(String fieldValue, String fieldName) {
        if (fieldValue.equals("")) throw new EmptyFieldError("Person", fieldName);
    }

    private void checkInvalidCharacter(String fieldValue, String fieldName) {
        for (int i = 0; i < fieldValue.length(); i++)
            if (!Character.isLetter(fieldValue.charAt(i)))
                throw new InvalidCharacterError(fieldName, fieldValue.charAt(i), i);
    }

    private void checkInvalidEmail(String fieldValue, String fieldName) {
        if (!fieldValue.matches("^[a-zA-Z\\d+_.-]+@[a-zA-Z\\d.-]+$"))
            throw new InvalidEmailError(fieldValue, fieldName);
    }
}
