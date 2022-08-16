package com.sadesoft.portal.core.service;

import java.util.List;

import com.sadesoft.portal.base.error.*;
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

    public List<Person> listPage(int pageNo, int pageSize) {
        return repo.listPage(pageNo, pageSize);
    }

    public Person read(String id) {
        return repo.read(id);
    }

    public Person create(Person person) {
        check(person);
        return repo.create(person);
    }

    public Person update(String id, Person person) {
        check(person);
        return repo.update(id, person);
    }

    public Person delete(String id) {
        return repo.delete(id);
    }

    private void check(Person person) {
        checkMissingFields(person);
        checkEmptyFields(person);
        checkInvalidCharacters(person);
        checkInvalidEmails(person);
        checkInvalidPhones(person);
        checkCopiedField(person.email, person.workEmail, "email", "workEmail");
        checkInvalidLengths(person);
    }

    private void checkMissingFields(Person person) {
        checkMissingField(person.firstName, "firstName");
        checkMissingField(person.lastName, "lastName");
        checkMissingField(person.email, "email");
        checkMissingField(person.workEmail, "workEmail");
        checkMissingField(person.mobilePhone, "mobilePhone");
        checkMissingField(person.workPhone, "workPhone");
        checkMissingField(person.employmentStartDate, "employmentStartDate");
        checkMissingField(person.workingDayId, "workingDayId");
        checkMissingField(person.accessType, "accessType");
        checkMissingField(person.birthday, "birthday");
        checkMissingField(person.bloodType, "bloodType");
        checkMissingField(person.educationLevel, "educationLevel");
        checkMissingField(person.graduated, "graduated");
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

    private void checkInvalidCharacters(Person person) {
        checkInvalidCharacter(person.firstName, "firstName");
        checkInvalidCharacter(person.lastName, "lastName");
    }

    private void checkInvalidEmails(Person person) {
        checkInvalidEmail(person.email, "email");
        checkInvalidEmail(person.workEmail, "workEmail");
    }

    private void checkInvalidPhones(Person person) {
        checkInvalidPhone(person.mobilePhone, "mobilePhone");
        if (!person.workPhone.isEmpty()) checkInvalidPhone(person.workPhone, "workPhone");
    }

    private void checkInvalidLengths(Person person) {
        checkInvalidLength(person.educationalInstitution.length(), "educationalInstitution", 150, 2);
        checkInvalidLength(person.nationality.length(), "nationality", 2, 2);
        if (person.nationality.equals("TR"))
            checkInvalidLength(person.idNumber.length(), "idNumber", 11, 11);
    }

    private void checkMissingField(Object value, String field) {
        if (value == null) throw new MissingFieldError(field);
    }

    private void checkEmptyField(String value, String field) {
        if (value.isEmpty()) throw new EmptyFieldError(field);
    }

    private void checkInvalidCharacter(String value, String field) {
        for (int i = 0; i < value.length(); i++)
            if (!Character.isLetter(value.charAt(i)))
                throw new InvalidCharacterError(field, value.charAt(i), i);
    }

    private void checkInvalidEmail(String value, String field) {
        if (!value.matches("^[a-zA-Z\\d+_.-]+@[a-zA-Z\\d.-]+$"))
            throw new InvalidEmailError(value, field);
    }

    private void checkInvalidPhone(String value, String field) {
        if (!value.matches("^\\+\\d+ \\d+$")) throw new InvalidPhoneError(value, field);
    }

    private void checkCopiedField(String value1, String value2, String field1, String field2) {
        if (value1.equals(value2)) throw new CopiedFieldError(field1, field2);
    }

    private void checkInvalidLength(int length, String field, int maxLength, int minLength) {
        if (length > maxLength || length < minLength) throw new InvalidLengthError(length, field);
    }
}
