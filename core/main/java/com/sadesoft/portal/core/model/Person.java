package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;
import java.time.LocalDate;

public class Person implements Record {
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

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
