package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;
import java.time.LocalDate;

public class Person implements Record {
    public Integer id;
    public String firstName;
    public String lastName;
    public String email;
    public String workEmail;
    public String mobilePhone;
    public String workPhone;
    public LocalDate employmentStartDate;
    public ContractType contractType;
    public LocalDate contractEndDate;
    public String workingDayId;
    public AccessType accessType;
    public LocalDate birthday;
    public BloodType bloodType;
    public EducationalLevel educationalLevel;
    public EducationStatus educationStatus;
    public String educationalInstitution;
    public Gender gender;
    public String idNumber;
    public LevelOfDisability levelOfDisability;
    public MaritalStatus maritalStatus;
    public Boolean partnerWorkStatus;
    public MilitaryService militaryService;
    public LocalDate militaryServiceDelayDate;
    public String nationality;
    public Integer numberOfChildren;
    public TourStatus tourStatus;

    public Person (){
        this.id = null;
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.workEmail = null;
        this.mobilePhone = null;
        this.workPhone = null;
        this.employmentStartDate = null;
        this.contractType = null;
        this.contractEndDate = null;
        this.workingDayId = null;
        this.accessType = null;
        this.birthday = null;
        this.bloodType = null;
        this.educationalLevel = null;
        this.educationStatus = null;
        this.educationalInstitution = null;
        this.gender = null;
        this.idNumber = null;
        this.levelOfDisability = null;
        this.maritalStatus = null;
        this.partnerWorkStatus = null;
        this.militaryService = null;
        this.militaryServiceDelayDate = null;
        this.nationality = null;
        this.numberOfChildren = null;
        this.tourStatus = null;
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
