package com.sadesoft.portal.repo.kolay;

import com.sadesoft.portal.base.repo.Record;

import java.util.Objects;

public class KolayPerson implements Record, Cloneable {
    public String id = null;
    public String firstName = null;
    public String lastName = null;
    public String email = null;
    public String workEmail = null;
    public String mobilePhone = null;
    public String workPhone = null;
    public String gender = null;
    public String accessType = null;
    public String nationality = null;
    public String idNumber = null;
    public String employmentStartDate = null;
    public String contractType = null;
    public String contractEndDate = null;
    public String birthday = null;
    public String maritalStatus = null;
    public Boolean partnerWorkStatus = null;
    public String militaryService = null;
    public String militaryServiceDelayDate = null;
    public String educationLevel = null;
    public String educationStatus = null;
    public String educationalInstitution = null;
    public String status = null;
    public String tourStatus = null;
    public String createdAt = null;
    public String updatedAt = null;
    public String levelOfDisability = null;
    public Integer numberOfChildren = null;
    public String bloodType = null;

    public KolayPerson () {
    }

    public KolayPerson(String id,
                  String firstName,
                  String lastName,
                  String email,
                  String workEmail,
                  String mobilePhone,
                  String workPhone,
                  String gender,
                  String accessType,
                  String nationality,
                  String idNumber,
                  String employmentStartDate,
                  String contractType,
                  String contractEndDate,
                  String birthday,
                  String maritalStatus,
                  Boolean partnerWorkStatus,
                  String militaryService,
                  String militaryServiceDelayDate,
                  String educationLevel,
                  String educationStatus,
                  String educationalInstitution,
                  String status,
                  String tourStatus,
                  String createdAt,
                  String updatedAt,
                  String levelOfDisability,
                  Integer numberOfChildren,
                  String bloodType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.workEmail = workEmail;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.employmentStartDate = employmentStartDate;
        this.contractEndDate = contractEndDate;
        this.accessType = accessType;
        this.birthday = birthday;
        this.bloodType = bloodType;
        this.educationLevel = educationLevel;
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
        this.contractType=contractType;
        this.status=status;
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
        this.educationStatus=educationStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KolayPerson that = (KolayPerson) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(workEmail, that.workEmail) &&
                Objects.equals(mobilePhone, that.mobilePhone) &&
                Objects.equals(workPhone, that.workPhone) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(accessType, that.accessType) &&
                Objects.equals(nationality, that.nationality) &&
                Objects.equals(idNumber, that.idNumber) &&
                Objects.equals(employmentStartDate, that.employmentStartDate) &&
                Objects.equals(contractType, that.contractType) &&
                Objects.equals(contractEndDate, that.contractEndDate) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(maritalStatus, that.maritalStatus) &&
                Objects.equals(partnerWorkStatus, that.partnerWorkStatus) &&
                Objects.equals(militaryService, that.militaryService) &&
                Objects.equals(militaryServiceDelayDate, that.militaryServiceDelayDate) &&
                Objects.equals(educationLevel, that.educationLevel) &&
                Objects.equals(educationStatus, that.educationStatus) &&
                Objects.equals(educationalInstitution, that.educationalInstitution) &&
                Objects.equals(status, that.status) &&
                Objects.equals(tourStatus, that.tourStatus) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(levelOfDisability, that.levelOfDisability) &&
                Objects.equals(numberOfChildren, that.numberOfChildren) &&
                Objects.equals(bloodType, that.bloodType);
    }

    @Override
    public String toString() {
        return "KolayPerson[" +
                "id=" + id +
                " firstName=" + firstName +
                " lastName=" + lastName +
                " email=" + email +
                " workEmail=" + workEmail +
                " mobilePhone=" + mobilePhone +
                " workPhone=" + workPhone +
                " gender=" + gender +
                " accessType=" + accessType +
                " nationality=" + nationality +
                " idNumber=" + idNumber +
                " employmentStartDate=" + employmentStartDate +
                " contractType=" + contractType +
                " contractEndDate=" + contractEndDate +
                " birthday=" + birthday +
                " maritalStatus=" + maritalStatus +
                " partnerWorkStatus=" + partnerWorkStatus +
                " militaryService=" + militaryService +
                " militaryServiceDelayDate=" + militaryServiceDelayDate +
                " educationLevel=" + educationLevel +
                " educationStatus=" + educationStatus +
                " educationalInstitution=" + educationalInstitution +
                " status=" + status +
                " tourStatus=" + tourStatus +
                " createdAt=" + createdAt +
                " updatedAt=" + updatedAt +
                " levelOfDisability=" + levelOfDisability +
                " numberOfChildren=" + numberOfChildren +
                " bloodType=" + bloodType +
                ']';
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public KolayPerson copy() {
        try {
            return (KolayPerson) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public KolayPerson withId(String id) {
        KolayPerson kolayPerson = copy();
        kolayPerson.id = id;
        return kolayPerson;
    }

    public KolayPerson withFirstName(String firstName) {
        KolayPerson kolayPerson = copy();
        kolayPerson.firstName = firstName;
        return kolayPerson;
    }

    public KolayPerson withLastName(String lastName) {
        KolayPerson kolayPerson = copy();
        kolayPerson.lastName = lastName;
        return kolayPerson;
    }

    public KolayPerson withEmail(String email) {
        KolayPerson kolayPerson = copy();
        kolayPerson.email = email;
        return kolayPerson;
    }

    public KolayPerson withWorkEmail(String workEmail) {
        KolayPerson kolayPerson = copy();
        kolayPerson.workEmail = workEmail;
        return kolayPerson;
    }

    public KolayPerson withMobilePhone(String mobilePhone) {
        KolayPerson kolayPerson = copy();
        kolayPerson.mobilePhone = mobilePhone;
        return kolayPerson;
    }

    public KolayPerson withWorkPhone(String workPhone) {
        KolayPerson kolayPerson = copy();
        kolayPerson.workPhone = workPhone;
        return kolayPerson;
    }

    public KolayPerson withGender(String gender) {
        KolayPerson kolayPerson = copy();
        kolayPerson.gender = gender;
        return kolayPerson;
    }

    public KolayPerson withAccessType(String accessType) {
        KolayPerson kolayPerson = copy();
        kolayPerson.accessType = accessType;
        return kolayPerson;
    }

    public KolayPerson withNationality(String nationality) {
        KolayPerson kolayPerson = copy();
        kolayPerson.nationality = nationality;
        return kolayPerson;
    }

    public KolayPerson withIdNumber(String idNumber) {
        KolayPerson kolayPerson = copy();
        kolayPerson.idNumber = idNumber;
        return kolayPerson;
    }

    public KolayPerson withEmploymentStartDate(String employmentStartDate) {
        KolayPerson kolayPerson = copy();
        kolayPerson.employmentStartDate = employmentStartDate;
        return kolayPerson;
    }

    public KolayPerson withContractType(String contractType) {
        KolayPerson kolayPerson = copy();
        kolayPerson.contractType = contractType;
        return kolayPerson;
    }

    public KolayPerson withContractEndDate(String contractEndDate) {
        KolayPerson kolayPerson = copy();
        kolayPerson.contractEndDate = contractEndDate;
        return kolayPerson;
    }

    public KolayPerson withBirthday(String birthday) {
        KolayPerson kolayPerson = copy();
        kolayPerson.birthday = birthday;
        return kolayPerson;
    }

    public KolayPerson withMaritalStatus(String maritalStatus) {
        KolayPerson kolayPerson = copy();
        kolayPerson.maritalStatus = maritalStatus;
        return kolayPerson;
    }

    public KolayPerson withPartnerWorkStatus(Boolean partnerWorkStatus) {
        KolayPerson kolayPerson = copy();
        kolayPerson.partnerWorkStatus = partnerWorkStatus;
        return kolayPerson;
    }

    public KolayPerson withMilitaryService(String militaryService) {
        KolayPerson kolayPerson = copy();
        kolayPerson.militaryService = militaryService;
        return kolayPerson;
    }

    public KolayPerson withMilitaryServiceDelayDate(String militaryServiceDelayDate) {
        KolayPerson kolayPerson = copy();
        kolayPerson.militaryServiceDelayDate = militaryServiceDelayDate;
        return kolayPerson;
    }

    public KolayPerson withEducationLevel(String educationLevel) {
        KolayPerson kolayPerson = copy();
        kolayPerson.educationLevel = educationLevel;
        return kolayPerson;
    }

    public KolayPerson withEducationStatus(String educationStatus) {
        KolayPerson kolayPerson = copy();
        kolayPerson.educationStatus = educationStatus;
        return kolayPerson;
    }

    public KolayPerson withEducationalInstitution(String educationalInstitution) {
        KolayPerson kolayPerson = copy();
        kolayPerson.educationalInstitution = educationalInstitution;
        return kolayPerson;
    }

    public KolayPerson withStatus(String status) {
        KolayPerson kolayPerson = copy();
        kolayPerson.status = status;
        return kolayPerson;
    }

    public KolayPerson withTourStatus(String tourStatus) {
        KolayPerson kolayPerson = copy();
        kolayPerson.tourStatus = tourStatus;
        return kolayPerson;
    }

    public KolayPerson withCreatedAt(String createdAt) {
        KolayPerson kolayPerson = copy();
        kolayPerson.createdAt = createdAt;
        return kolayPerson;
    }

    public KolayPerson withUpdatedAt(String updatedAt) {
        KolayPerson kolayPerson = copy();
        kolayPerson.updatedAt = updatedAt;
        return kolayPerson;
    }

    public KolayPerson withLevelOfDisability(String levelOfDisability) {
        KolayPerson kolayPerson = copy();
        kolayPerson.levelOfDisability = levelOfDisability;
        return kolayPerson;
    }

    public KolayPerson withNumberOfChildren(Integer numberOfChildren) {
        KolayPerson kolayPerson = copy();
        kolayPerson.numberOfChildren = numberOfChildren;
        return kolayPerson;
    }

    public KolayPerson withBloodType(String bloodType) {
        KolayPerson kolayPerson = copy();
        kolayPerson.bloodType = bloodType;
        return kolayPerson;
    }
}
