package com.sadesoft.portal.repo.local;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.*;
import com.sadesoft.portal.core.model.EducationLevel;
import com.sadesoft.portal.core.repo.PersonRepo;

public class LocalPersonRepo extends JdbcRepo<Person> implements PersonRepo {
    public LocalPersonRepo(Connection connection) {
        super(connection, "people", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("first_name"),
                new JdbcColumn("last_name"),
                new JdbcColumn("email"),
                new JdbcColumn("work_email"),
                new JdbcColumn("mobile_phone"),
                new JdbcColumn("work_phone"),
                new JdbcColumn("employment_start_date"),
                new JdbcColumn("contract_end_date"),
                new JdbcColumn("access_type", "access_type"),
                new JdbcColumn("birthday"),
                new JdbcColumn("blood_type", "blood_type"),
                new JdbcColumn("education_level", "education_level"),
                new JdbcColumn("graduated", "graduated"),
                new JdbcColumn("educational_institution"),
                new JdbcColumn("gender", "gender"),
                new JdbcColumn("id_number"),
                new JdbcColumn("level_of_disability", "level_of_disability"),
                new JdbcColumn("marital_status", "marital_status"),
                new JdbcColumn("partner_work_status"),
                new JdbcColumn("military_service", "military_service"),
                new JdbcColumn("military_service_delay_date"),
                new JdbcColumn("nationality"),
                new JdbcColumn("number_of_children"),
                new JdbcColumn("active"),
                new JdbcColumn("tour_status", "tour_status")));
    }

    @Override
    public Person toObject(ResultSet s) throws SQLException {
        int i = 0;
        return new Person(
                getId(s, getColumnName(i++)),
                s.getString(getColumnName(i++)),
                s.getString(getColumnName(i++)),
                s.getString(getColumnName(i++)),
                s.getString(getColumnName(i++)),
                s.getString(getColumnName(i++)),
                s.getString(getColumnName(i++)),
                getLocalDate(s, getColumnName(i++)),
                getLocalDate(s, getColumnName(i++)),
                AccessType.valueOf(s.getString(getColumnName(i++))),
                getLocalDate(s, getColumnName(i++)),
                BloodType.valueOf(s.getString(getColumnName(i++))),
                EducationLevel.valueOf(s.getString(getColumnName(i++))),
                s.getBoolean(getColumnName(i++)),
                s.getString(getColumnName(i++)),
                Gender.valueOf(s.getString(getColumnName(i++))),
                s.getString(getColumnName(i++)),
                LevelOfDisability.valueOf(s.getString(getColumnName(i++))),
                MaritalStatus.valueOf(s.getString(getColumnName(i++))),
                s.getBoolean(getColumnName(i++)),
                MilitaryService.valueOf(s.getString(getColumnName(i++))),
                getLocalDate(s, getColumnName(i++)),
                s.getString(getColumnName(i++)),
                s.getInt(getColumnName(i++)),
                s.getBoolean(getColumnName(i++)),
                TourStatus.valueOf(s.getString(getColumnName(i)))
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
        setLocalDate(s, i++, o.contractEndDate);
        s.setString(i++, o.accessType.name());
        setLocalDate(s, i++, o.birthday);
        s.setString(i++, o.bloodType.name());
        s.setString(i++, o.educationLevel.name());
        s.setBoolean(i++, o.graduated);
        s.setString(i++, o.educationalInstitution);
        s.setString(i++, o.gender.name());
        s.setString(i++, o.idNumber);
        s.setString(i++, o.levelOfDisability.name());
        s.setString(i++, o.maritalStatus.name());
        s.setBoolean(i++, o.partnerWorkStatus);
        s.setString(i++, o.militaryService.name());
        setLocalDate(s, i++, o.militaryServiceDelayDate);
        s.setString(i++, o.nationality);
        s.setInt(i++, o.numberOfChildren);
        s.setBoolean(i++, o.active);
        s.setString(i++, o.tourStatus.name());
        if (o.id != null) setId(s, i, o.id);
    }
}
