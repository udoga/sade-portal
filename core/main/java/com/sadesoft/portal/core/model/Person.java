package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;
import java.time.LocalDate;

public class Person implements Record, Cloneable {
    public Integer id = null;
    public String firstName = null;
    public String lastName = null;
    public String email = null;
    public String workEmail = null;
    public String mobilePhone = null;
    public String workPhone = null;
    public LocalDate employmentStartDate = null;
    public ContractType contractType = null;
    public LocalDate contractEndDate = null;
    public String workingDayId = null;
    public AccessType accessType = null;
    public LocalDate birthday = null;
    public BloodType bloodType = null;
    public EducationalLevel educationalLevel = null;
    public EducationStatus educationStatus = null;
    public String educationalInstitution = null;
    public Gender gender = null;
    public String idNumber = null;
    public LevelOfDisability levelOfDisability = null;
    public MaritalStatus maritalStatus = null;
    public Boolean partnerWorkStatus = null;
    public MilitaryService militaryService = null;
    public LocalDate militaryServiceDelayDate = null;
    public String nationality = null;
    public Integer numberOfChildren = null;
    public TourStatus tourStatus = null;

    public Person () {
    }

    public Person(Integer id,
                  String firstName,
                  String lastName,
                  String email,
                  String workEmail,
                  String mobilePhone,
                  String workPhone,
                  LocalDate employmentStartDate,
                  ContractType contractType,
                  LocalDate contractEndDate,
                  String workingDayId,
                  AccessType accessType,
                  LocalDate birthday,
                  BloodType bloodType,
                  EducationalLevel educationalLevel,
                  EducationStatus educationStatus,
                  String educationalInstitution,
                  Gender gender,
                  String idNumber,
                  LevelOfDisability levelOfDisability,
                  MaritalStatus maritalStatus,
                  Boolean partnerWorkStatus,
                  MilitaryService militaryService,
                  LocalDate militaryServiceDelayDate,
                  String nationality,
                  Integer numberOfChildren,
                  TourStatus tourStatus) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.workEmail = workEmail;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.employmentStartDate = employmentStartDate;
        this.contractType = contractType;
        this.contractEndDate = contractEndDate;
        this.workingDayId = workingDayId;
        this.accessType = accessType;
        this.birthday = birthday;
        this.bloodType = bloodType;
        this.educationalLevel = educationalLevel;
        this.educationStatus = educationStatus;
        this.educationalInstitution = educationalInstitution;
        this.gender = gender;
        this.idNumber = idNumber;
        this.levelOfDisability = levelOfDisability;
        this.maritalStatus = maritalStatus;
        this.partnerWorkStatus = partnerWorkStatus;
        this.militaryService = militaryService;
        this.militaryServiceDelayDate = militaryServiceDelayDate;
        this.nationality = nationality;
        this.numberOfChildren = numberOfChildren;
        this.tourStatus = tourStatus;
    }

    public Person(String firstName,
                  String lastName,
                  String email,
                  String workEmail,
                  String mobilePhone,
                  String workPhone,
                  LocalDate employmentStartDate,
                  ContractType contractType,
                  LocalDate contractEndDate,
                  String workingDayId,
                  AccessType accessType,
                  LocalDate birthday,
                  BloodType bloodType,
                  EducationalLevel educationalLevel,
                  EducationStatus educationStatus,
                  String educationalInstitution,
                  Gender gender,
                  String idNumber,
                  LevelOfDisability levelOfDisability,
                  MaritalStatus maritalStatus,
                  Boolean partnerWorkStatus,
                  MilitaryService militaryService,
                  LocalDate militaryServiceDelayDate,
                  String nationality,
                  Integer numberOfChildren,
                  TourStatus tourStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.workEmail = workEmail;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.employmentStartDate = employmentStartDate;
        this.contractType = contractType;
        this.contractEndDate = contractEndDate;
        this.workingDayId = workingDayId;
        this.accessType = accessType;
        this.birthday = birthday;
        this.bloodType = bloodType;
        this.educationalLevel = educationalLevel;
        this.educationStatus = educationStatus;
        this.educationalInstitution = educationalInstitution;
        this.gender = gender;
        this.idNumber = idNumber;
        this.levelOfDisability = levelOfDisability;
        this.maritalStatus = maritalStatus;
        this.partnerWorkStatus = partnerWorkStatus;
        this.militaryService = militaryService;
        this.militaryServiceDelayDate = militaryServiceDelayDate;
        this.nationality = nationality;
        this.numberOfChildren = numberOfChildren;
        this.tourStatus = tourStatus;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Person withId(Integer id) {
        Person person = copy();
        person.id = id;
        return person;
    }

    public Person withFirstName(String firstName) {
        Person person = copy();
        person.firstName = firstName;
        return person;
    }

    public Person withLastName(String lastName) {
        Person person = copy();
        person.lastName = lastName;
        return person;
    }

    public Person withEmail(String email) {
        Person person = copy();
        person.email = email;
        return person;
    }

    public Person withWorkEmail(String workEmail) {
        Person person = copy();
        person.workEmail = workEmail;
        return person;
    }

    public Person withMobilePhone(String mobilePhone) {
        Person person = copy();
        person.mobilePhone = mobilePhone;
        return person;
    }

    public Person withWorkPhone(String workEmail) {
        Person person = copy();
        person.workEmail = workEmail;
        return person;
    }

    public Person withEmploymentStartDate(LocalDate employmentStartDate) {
        Person person = copy();
        person.employmentStartDate = employmentStartDate;
        return person;
    }

    public Person withContractType(ContractType contractType) {
        Person person = copy();
        person.contractType = contractType;
        return person;
    }

    public Person withContractEndDate(LocalDate contractEndDate) {
        Person person = copy();
        person.contractEndDate = contractEndDate;
        return person;
    }

    public Person withWorkingDayId(String workingDayId) {
        Person person = copy();
        person.workingDayId = workingDayId;
        return person;
    }

    public Person withAccessType(AccessType accessType) {
        Person person = copy();
        person.accessType = accessType;
        return person;
    }

    public Person withBirthday(LocalDate birthday) {
        Person person = copy();
        person.birthday = birthday;
        return person;
    }

    public Person withBloodType(BloodType bloodType) {
        Person person = copy();
        person.bloodType = bloodType;
        return person;
    }

    public Person withEducationalLevel(EducationalLevel educationalLevel) {
        Person person = copy();
        person.educationalLevel = educationalLevel;
        return person;
    }

    public Person withEducationStatus(EducationStatus educationStatus) {
        Person person = copy();
        person.educationStatus = educationStatus;
        return person;
    }

    public Person withEducationalInstitution(String educationalInstitution) {
        Person person = copy();
        person.educationalInstitution = educationalInstitution;
        return person;
    }

    public Person withGender(Gender gender) {
        Person person = copy();
        person.gender = gender;
        return person;
    }

    public Person withIdNumber(String idNumber) {
        Person person = copy();
        person.idNumber = idNumber;
        return person;
    }

    public Person withLevelOfDisability(LevelOfDisability levelOfDisability) {
        Person person = copy();
        person.levelOfDisability = levelOfDisability;
        return person;
    }

    public Person withMaritalStatus(MaritalStatus maritalStatus) {
        Person person = copy();
        person.maritalStatus = maritalStatus;
        return person;
    }

    public Person withPartnerWorkStatus(Boolean partnerWorkStatus) {
        Person person = copy();
        person.partnerWorkStatus = partnerWorkStatus;
        return person;
    }

    public Person withMilitaryService(MilitaryService militaryService) {
        Person person = copy();
        person.militaryService = militaryService;
        return person;
    }

    public Person withMilitaryServiceDelayDate(LocalDate militaryServiceDelayDate) {
        Person person = copy();
        person.militaryServiceDelayDate = militaryServiceDelayDate;
        return person;
    }

    public Person withNationality(String nationality) {
        Person person = copy();
        person.nationality = nationality;
        return person;
    }

    public Person withNumberOfChildren(Integer numberOfChildren) {
        Person person = copy();
        person.numberOfChildren = numberOfChildren;
        return person;
    }

    public Person withTourStatus(TourStatus tourStatus) {
        Person person = copy();
        person.tourStatus = tourStatus;
        return person;
    }

    private Person copy() {
        try {
            return (Person) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
