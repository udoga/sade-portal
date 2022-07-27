package com.sadesoft.portal.core.service;

import java.util.List;

import com.sadesoft.portal.base.error.EmptyFieldError;
import com.sadesoft.portal.base.error.InvalidCharacterError;
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
        validate(person);
        return repo.create(person);
    }

    public Person update(int id, Person person) {
        validate(person);
        return repo.update(id, person);
    }

    public Person delete(int id) {
        return repo.delete(id);
    }

    private void validate(Person person) {
        validateExists(person.firstName, "firstName");
        validateExists(person.lastName, "lastName");
        validateExists(person.email, "email");
        validateExists(person.workEmail, "workEmail");
        validateExists(person.mobilePhone, "mobilePhone");
        validateExists(person.workPhone, "workPhone");
        validateExists(person.employmentStartDate, "employmentStartDate");
        validateExists(person.contractType, "contractType");
        validateExists(person.contractEndDate, "contractEndDate");
        validateExists(person.workingDayId, "workingDayId");
        validateExists(person.accessType, "accessType");
        validateExists(person.birthday, "birthday");
        validateExists(person.bloodType, "bloodType");
        validateExists(person.educationalLevel, "educationalLevel");
        validateExists(person.educationStatus, "educationStatus");
        validateExists(person.educationalInstitution, "educationalInstitution");
        validateExists(person.gender, "gender");
        validateExists(person.idNumber, "idNumber");
        validateExists(person.levelOfDisability, "levelOfDisability");
        validateExists(person.maritalStatus, "maritalStatus");
        validateExists(person.partnerWorkStatus, "partnerWorkStatus");
        validateExists(person.militaryService, "militaryService");
        validateExists(person.militaryServiceDelayDate, "militaryServiceDelayDate");
        validateExists(person.nationality, "nationality");
        validateExists(person.numberOfChildren, "numberOfChildren");
        validateExists(person.tourStatus, "tourStatus");
        validateCharacters(person.firstName, "firstName");
        validateCharacters(person.lastName, "lastName");
        validateFilled(person.firstName, "firstName");
        validateFilled(person.lastName, "lastName");
    }

    private void validateExists(Object fieldValue, String fieldName) {
        if (fieldValue == null) throw new MissingFieldError("Person", fieldName);
    }

    private void validateFilled(String fieldValue, String fieldName) {
        if (fieldValue.equals("")) throw new EmptyFieldError("Person", fieldName);
    }

    private void validateCharacters(String fieldValue, String fieldName) {
        for (int i = 0; i < fieldValue.length(); i++)
            if (!Character.isLetter(fieldValue.charAt(i)))
                throw new InvalidCharacterError(fieldName, fieldValue.charAt(i), i);
    }
}
