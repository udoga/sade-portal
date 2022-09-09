package com.sadesoft.portal.repo.kolay;

import com.sadesoft.portal.base.error.InvalidPhoneError;
import com.sadesoft.portal.base.repo.Record;
import com.sadesoft.portal.core.model.*;

import java.time.LocalDate;
import java.util.List;
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
    public String partnerWorkStatus = null;
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
    public List<KolayUnit> unitList = null;
    public List<KolayData> dataList = null;


    public KolayPerson () {
    }

    public KolayPerson(
            String id,
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
            String partnerWorkStatus,
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
            String bloodType,
            List<KolayUnit> unitList,
            List<KolayData> dataList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.workEmail = workEmail;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.gender = gender;
        this.accessType = accessType;
        this.nationality = nationality;
        this.idNumber = idNumber;
        this.employmentStartDate = employmentStartDate;
        this.contractType=contractType;
        this.contractEndDate = contractEndDate;
        this.birthday = birthday;
        this.maritalStatus = maritalStatus;
        this.partnerWorkStatus = partnerWorkStatus;
        this.militaryService = militaryService;
        this.militaryServiceDelayDate = militaryServiceDelayDate;
        this.educationLevel = educationLevel;
        this.educationStatus=educationStatus;
        this.educationalInstitution = educationalInstitution;
        this.status=status;
        this.tourStatus = tourStatus;
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
        this.levelOfDisability = levelOfDisability;
        this.numberOfChildren = numberOfChildren;
        this.bloodType = bloodType;
        this.unitList = unitList;
        this.dataList = dataList;
    }

    public static KolayPerson fromPerson(Person p) {
        return new KolayPerson(
                p.id,
                p.firstName,
                p.lastName,
                p.email,
                p.workEmail,
                toKolayPhoneNumber(p.mobilePhone),
                toKolayPhoneNumber(p.workPhone),
                toKolayEnumString(p.gender),
                toKolayEnumString(p.accessType),
                p.nationality,
                p.idNumber,
                toKolayDateString(p.employmentStartDate),
                toKolayContractType(p.contractEndDate),
                toKolayDateString(p.contractEndDate),
                toKolayDateString(p.birthday),
                toKolayEnumString(p.maritalStatus),
                toKolayPartnerWorkStatus(p.partnerWorkStatus),
                toKolayEnumString(p.militaryService),
                toKolayDateString(p.militaryServiceDelayDate),
                toKolayEnumString(p.educationLevel),
                toKolayEducationStatus(p.graduated),
                p.educationalInstitution,
                toKolayStatus(p.active),
                toKolayEnumString(p.tourStatus),
                null,
                null,
                toKolayEnumString(p.levelOfDisability),
                p.numberOfChildren,
                toKolayBloodType(p.bloodType),
                null,
                null);
    }

    public Person toPerson() {
        return new Person(
                this.id,
                this.firstName,
                this.lastName,
                this.email,
                this.workEmail,
                toPhoneNumber(this.mobilePhone),
                toPhoneNumber(this.workPhone),
                toLocalDate(this.employmentStartDate),
                toLocalDate(this.contractEndDate),
                toEnum(this.accessType, AccessType.class),
                toLocalDate(this.birthday),
                toBloodType(this.bloodType),
                toEnum(this.educationLevel, EducationLevel.class),
                toGraduated(this.educationStatus),
                this.educationalInstitution,
                toEnum(this.gender, Gender.class),
                this.idNumber,
                toEnum(this.levelOfDisability, LevelOfDisability.class),
                toEnum(this.maritalStatus, MaritalStatus.class),
                toPartnerWorkStatus(this.partnerWorkStatus),
                toEnum(this.militaryService, MilitaryService.class),
                toLocalDate(this.militaryServiceDelayDate),
                this.nationality,
                this.numberOfChildren,
                toActive(this.status),
                toEnum(this.tourStatus, TourStatus.class));
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof KolayPerson)) return false;
        KolayPerson p = (KolayPerson) o;
        return Objects.equals(id, p.id) &&
                Objects.equals(firstName, p.firstName) &&
                Objects.equals(lastName, p.lastName) &&
                Objects.equals(email, p.email) &&
                Objects.equals(workEmail, p.workEmail) &&
                Objects.equals(mobilePhone, p.mobilePhone) &&
                Objects.equals(workPhone, p.workPhone) &&
                Objects.equals(gender, p.gender) &&
                Objects.equals(accessType, p.accessType) &&
                Objects.equals(nationality, p.nationality) &&
                Objects.equals(idNumber, p.idNumber) &&
                Objects.equals(employmentStartDate, p.employmentStartDate) &&
                Objects.equals(contractType, p.contractType) &&
                Objects.equals(contractEndDate, p.contractEndDate) &&
                Objects.equals(birthday, p.birthday) &&
                Objects.equals(maritalStatus, p.maritalStatus) &&
                Objects.equals(partnerWorkStatus, p.partnerWorkStatus) &&
                Objects.equals(militaryService, p.militaryService) &&
                Objects.equals(militaryServiceDelayDate, p.militaryServiceDelayDate) &&
                Objects.equals(educationLevel, p.educationLevel) &&
                Objects.equals(educationStatus, p.educationStatus) &&
                Objects.equals(educationalInstitution, p.educationalInstitution) &&
                Objects.equals(status, p.status) &&
                Objects.equals(tourStatus, p.tourStatus) &&
                Objects.equals(createdAt, p.createdAt) &&
                Objects.equals(updatedAt, p.updatedAt) &&
                Objects.equals(levelOfDisability, p.levelOfDisability) &&
                Objects.equals(numberOfChildren, p.numberOfChildren) &&
                Objects.equals(bloodType, p.bloodType) &&
                Objects.equals(unitList, p.unitList) &&
                Objects.equals(dataList, p.dataList);
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
                " unitList=" + unitList +
                " dataList=" + dataList +
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

    private String toPhoneNumber(String num) {
        if (num == null) return null;
        if (!num.matches("^\\+\\d+$") || num.length() < 8) throw new InvalidPhoneError(num,"");
        return (num.substring(0, 3) + " " + num.substring(3));
    }

    private Boolean toPartnerWorkStatus(String s) {
        if (s == null) return null;
        if (s.equals("working")) return true;
        if (s.equals("notWorking")) return false;
        throw new RuntimeException("Invalid partner work status: " + s);
    }

    private Boolean toGraduated(String s) {
        if (s == null) return null;
        if (s.equals("graduate")) return true;
        if (s.equals("student")) return false;
        throw new RuntimeException("Invalid education status: " + s);
    }

    private Boolean toActive(String s) {
        if (s == null) return null;
        if (s.equals("active")) return true;
        if (s.equals("terminated")) return false;
        throw new RuntimeException("Invalid status: " + s);
    }

    private BloodType toBloodType(String s) {
        return s == null? null : BloodType.valueOf(s
                .replace("0", "Zero")
                .replace("-", "Negative")
                .replace("+", "Positive"));
    }

    private LocalDate toLocalDate(String date) {
        return date == null? null : LocalDate.parse(date);
    }

    private <T extends Enum<T>> T toEnum(String str, Class<T> type) {
        return str == null? null : T.valueOf(type, capitalize(str));
    }

    private static String toKolayPartnerWorkStatus(Boolean stat) {
        if (stat == null) return null;
        return stat? "working" : "notWorking";
    }

    private static String toKolayStatus(Boolean stat) {
        if (stat == null) return null;
        return stat? "active" : "terminated";
    }

    private static String toKolayEducationStatus(Boolean graduated) {
        if (graduated == null) return null;
        return graduated? "graduate" : "student";
    }

    private static String toKolayContractType(LocalDate date) {
        return date == null? "indefinite" : "definite";
    }

    private static String toKolayBloodType(BloodType bt) {
        return bt == null? null : (bt.toString()
                .replace("Zero", "0")
                .replace("Negative", "-")
                .replace("Positive", "+"));
    }

    private static String toKolayPhoneNumber(String num) {
        return num == null? null : (num.substring(0, 3) + num.substring(4));
    }

    private static <T> String toKolayEnumString(T value) {
        return value == null? null : uncapitalize(value.toString());
    }

    private static <T> String toKolayDateString(T value) {
        return value == null? null : value.toString();
    }

    private String capitalize(String str) {
        return (str.substring(0, 1).toUpperCase() + str.substring(1));
    }

    private static String uncapitalize(String str) {
        return (str.substring(0, 1).toLowerCase() + str.substring(1));
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

    public KolayPerson withPartnerWorkStatus(String partnerWorkStatus) {
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

    public KolayPerson withUnitList(List<KolayUnit> unitList) {
        KolayPerson kolayPerson = copy();
        kolayPerson.unitList = unitList;
        return kolayPerson;
    }

    public KolayPerson withDataList(List<KolayData> dataList) {
        KolayPerson kolayPerson = copy();
        kolayPerson.dataList = dataList;
        return kolayPerson;
    }
}
