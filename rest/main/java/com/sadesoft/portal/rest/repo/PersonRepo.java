package com.sadesoft.portal.rest.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Person;
import com.sadesoft.portal.core.model.AccessType;
import com.sadesoft.portal.core.model.BloodType;
import com.sadesoft.portal.core.model.ContractType;
import com.sadesoft.portal.core.model.EducationalLevel;
import com.sadesoft.portal.core.model.EducationStatus;
import com.sadesoft.portal.core.model.Gender;
import com.sadesoft.portal.core.model.LevelOfDisability;
import com.sadesoft.portal.core.model.MaritalStatus;
import com.sadesoft.portal.core.model.MilitaryService;
import com.sadesoft.portal.core.model.TourStatus;

public class PersonRepo extends JdbcRepo<Person>{
    public PersonRepo(Connection connection) {
        super(connection, "people",
                List.of("id", "first_name", "last_name", "email", "work_email", "mobile_phone",
                "work_phone", "employment_start_date", "contract_type", "contract_end_date", "working_day_id",
                "access_type", "birthday", "blood_type", "educational_level", "education_status",
                "educational_institution", "gender", "id_number", "level_of_disability", "marital_status",
                "partner_work_status", "military_service", "military_service_delay_date", "nationality",
                "number_of_children", "tour_status"),
                List.of("access_type", "blood_type", "contract_type",
                "educational_level", "education_status", "gender", "level_of_disability", "marital_status",
                "military_service", "tour_status"));
    }

    @Override
    public Person toObject(ResultSet s) throws SQLException {
        int i = 0;
        return new Person(
                s.getInt(columnNames.get(i++)),
                s.getString(columnNames.get(i++)),
                s.getString(columnNames.get(i++)),
                s.getString(columnNames.get(i++)),
                s.getString(columnNames.get(i++)),
                s.getString(columnNames.get(i++)),
                s.getString(columnNames.get(i++)),
                getLocalDate(s, columnNames.get(i++)),
                ContractType.from(s.getString(columnNames.get(i++))),
                getLocalDate(s, columnNames.get(i++)),
                s.getString(columnNames.get(i++)),
                AccessType.from(s.getString(columnNames.get(i++))),
                getLocalDate(s, columnNames.get(i++)),
                BloodType.from(s.getString(columnNames.get(i++))),
                EducationalLevel.from(s.getString(columnNames.get(i++))),
                EducationStatus.from(s.getString(columnNames.get(i++))),
                s.getString(columnNames.get(i++)),
                Gender.from(s.getString(columnNames.get(i++))),
                s.getString(columnNames.get(i++)),
                LevelOfDisability.from(s.getString(columnNames.get(i++))),
                MaritalStatus.from(s.getString(columnNames.get(i++))),
                s.getBoolean(columnNames.get(i++)),
                MilitaryService.from(s.getString(columnNames.get(i++))),
                getLocalDate(s, columnNames.get(i++)),
                s.getString(columnNames.get(i++)),
                s.getInt(columnNames.get(i++)),
                TourStatus.from(s.getString(columnNames.get(i)))
        );
    }

    @Override
    protected void fillStatement(PreparedStatement s, Person o) throws SQLException {
        int i = 1;
        s.setString(i++, o.firstName);
        s.setString(i++, o.lastName);
        s.setString(i++, o.email);
        s.setString(i++, o.workEmail);
        s.setString(i++, o.mobilePhone);
        s.setString(i++, o.workPhone);
        setLocalDate(s, i++, o.employmentStartDate);
        s.setString(i++, (o.contractType == null)? "" : o.contractType.name());
        setLocalDate(s, i++, o.contractEndDate);
        s.setString(i++, o.workingDayId);
        s.setString(i++, (o.accessType == null)? "" : o.accessType.name());
        setLocalDate(s, i++, o.birthday);
        s.setString(i++, (o.bloodType == null)? "" : o.bloodType.name());
        s.setString(i++, (o.educationalLevel == null)? "" : o.educationalLevel.name());
        s.setString(i++, (o.educationStatus == null)? "" : o.educationStatus.name());
        s.setString(i++, o.educationalInstitution);
        s.setString(i++, (o.gender == null)? "" : o.gender.name());
        s.setString(i++, o.idNumber);
        s.setString(i++, (o.levelOfDisability == null)? "" : o.levelOfDisability.name());
        s.setString(i++, (o.maritalStatus == null)? "" : o.maritalStatus.name());
        s.setBoolean(i++, o.partnerWorkStatus);
        s.setString(i++, (o.militaryService == null)? "" : o.militaryService.name());
        setLocalDate(s, i++, o.militaryServiceDelayDate);
        s.setString(i++, o.nationality);
        s.setInt(i++, o.numberOfChildren);
        s.setString(i++, (o.tourStatus == null)? "" : o.tourStatus.name());
        if (o.id != null) s.setInt(i, o.id);
    }
}
