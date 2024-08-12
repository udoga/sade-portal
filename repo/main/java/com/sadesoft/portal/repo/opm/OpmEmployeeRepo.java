package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedEmployee;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.repo.EmployeeRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OpmEmployeeRepo extends JdbcRepo<Employee> implements EmployeeRepo {
    public OpmEmployeeRepo(Connection connection) {
        super(connection, "opm_hrm_employee", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("address"),
                new JdbcColumn("bank_name"),
                new JdbcColumn("birth_place"),
                new JdbcColumn("birthday"),
                new JdbcColumn("blood_type"),
                new JdbcColumn("branch_id"),
                new JdbcColumn("city"),
                new JdbcColumn("country"),
                new JdbcColumn("created_by"),
                new JdbcColumn("created_time"),
                new JdbcColumn("disabled_description"),
                new JdbcColumn("disabled_rate"),
                new JdbcColumn("disabled_status"),
                new JdbcColumn("driving_license"),
                new JdbcColumn("email_personal"),
                new JdbcColumn("email_work"),
                new JdbcColumn("employee_code"),
                new JdbcColumn("employee_type_id"),
                new JdbcColumn("father_name"),
                new JdbcColumn("first_aid_card"),
                new JdbcColumn("first_aid_card_expiration_date"),
                new JdbcColumn("first_employment_date"),
                new JdbcColumn("food_card_first_month_amount"),
                new JdbcColumn("food_card_type"),
                new JdbcColumn("fuel_right_end_date"),
                new JdbcColumn("function"),
                new JdbcColumn("gender"),
                new JdbcColumn("iban"),
                new JdbcColumn("identity_card_no"),
                new JdbcColumn("identity_card_serial_no"),
                new JdbcColumn("identity_card_validity_date"),
                new JdbcColumn("last_updated_by"),
                new JdbcColumn("last_updated_time"),
                new JdbcColumn("leave_date"),
                new JdbcColumn("leave_description"),
                new JdbcColumn("level_id"),
                new JdbcColumn("maiden_name"),
                new JdbcColumn("manager_id"),
                new JdbcColumn("marital_status"),
                new JdbcColumn("marriage_date"),
                new JdbcColumn("military_description"),
                new JdbcColumn("military_discharge_date"),
                new JdbcColumn("military_status"),
                new JdbcColumn("mobile_personal"),
                new JdbcColumn("mobile_work"),
                new JdbcColumn("mother_name"),
                new JdbcColumn("name"),
                new JdbcColumn("nationality"),
                new JdbcColumn("organization_id"),
                new JdbcColumn("organization_node_id"),
                new JdbcColumn("passport_no"),
                new JdbcColumn("payment_type"),
                new JdbcColumn("phone_extension"),
                new JdbcColumn("position_code_id"),
                new JdbcColumn("position_id"),
                new JdbcColumn("postal_code"),
                new JdbcColumn("reference_person_id"),
                new JdbcColumn("salary_bucket_id"),
                new JdbcColumn("salary_currency"),
                new JdbcColumn("salary_effective_date"),
                new JdbcColumn("salary_end_date"),
                new JdbcColumn("salary_gross"),
                new JdbcColumn("salary_net"),
                new JdbcColumn("spouse_name"),
                new JdbcColumn("start_date"),
                new JdbcColumn("status"),
                new JdbcColumn("surname"),
                new JdbcColumn("user_id"),
                new JdbcColumn("uuid")));
    }

    @Override
    protected Employee toObject(ResultSet s) throws SQLException {
        return new Employee(
                getId(s, "id"),
                getId(s, "user_id"),
                s.getString("name"),
                s.getString("surname"));
    }

    @Override
    protected PreparedStatement fillStatement(PreparedStatement s, Employee o) throws SQLException {
        int i = 1;
        boolean inserting = (o.id == null);
        s.setNull(i++, java.sql.Types.VARCHAR); //address
        s.setNull(i++, java.sql.Types.VARCHAR); //bank name
        s.setNull(i++, java.sql.Types.VARCHAR); //birth place
        s.setNull(i++, java.sql.Types.TIMESTAMP);//birthday
        s.setNull(i++, java.sql.Types.VARCHAR); //blood type
        s.setNull(i++, java.sql.Types.BIGINT); //branch id
        s.setNull(i++, java.sql.Types.VARCHAR); //city
        s.setNull(i++, java.sql.Types.VARCHAR); //country
        if (inserting) s.setInt(i++, 0); //created by
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now()); //created time
        s.setNull(i++, java.sql.Types.VARCHAR); //disabled description
        s.setNull(i++, java.sql.Types.INTEGER); //disabled rate
        s.setNull(i++, java.sql.Types.VARCHAR); //disabled status
        s.setNull(i++, java.sql.Types.VARCHAR); //driving license
        s.setNull(i++, java.sql.Types.VARCHAR); //email personal
        s.setNull(i++, java.sql.Types.VARCHAR); //email work
        s.setNull(i++, java.sql.Types.VARCHAR); //employee code
        s.setNull(i++, java.sql.Types.BIGINT); //employee type id
        s.setNull(i++, java.sql.Types.VARCHAR); //father name
        s.setNull(i++, java.sql.Types.VARCHAR); //first aid card
        s.setNull(i++, java.sql.Types.TIMESTAMP); //first aid card expiration date
        s.setNull(i++, java.sql.Types.TIMESTAMP);  //first employment date
        s.setNull(i++, java.sql.Types.NUMERIC); //food card first month amount
        s.setNull(i++, java.sql.Types.VARCHAR); //food card type5
        s.setNull(i++, java.sql.Types.TIMESTAMP); //fuel right end date
        s.setNull(i++, java.sql.Types.VARCHAR); //function
        s.setNull(i++, java.sql.Types.VARCHAR); //gender
        s.setNull(i++, java.sql.Types.VARCHAR);//iban
        s.setNull(i++, java.sql.Types.VARCHAR);//identity card no
        s.setNull(i++, java.sql.Types.VARCHAR);//identity card serial no
        s.setNull(i++, java.sql.Types.TIMESTAMP); //identity card validity date
        s.setInt(i++, 0); //last updated by
        setLocalDateTime(s, i++, LocalDateTime.now()); //last updated time
        s.setNull(i++, java.sql.Types.TIMESTAMP); //leave date
        s.setNull(i++, java.sql.Types.VARCHAR); //leave description
        s.setNull(i++, java.sql.Types.BIGINT); //level id
        s.setNull(i++, java.sql.Types.VARCHAR); //maiden name
        s.setNull(i++, java.sql.Types.BIGINT); //manager id
        s.setNull(i++, java.sql.Types.VARCHAR); //marital status
        s.setNull(i++, java.sql.Types.TIMESTAMP); //marriage date
        s.setNull(i++, java.sql.Types.VARCHAR); //military description
        s.setNull(i++, java.sql.Types.TIMESTAMP); //military discharge date
        s.setNull(i++, java.sql.Types.VARCHAR); //military status
        s.setNull(i++, java.sql.Types.VARCHAR); //mobile personal
        s.setNull(i++, java.sql.Types.VARCHAR); //mobile work
        s.setNull(i++, java.sql.Types.VARCHAR); //mother name
        s.setString(i++, o.firstName);
        s.setNull(i++, java.sql.Types.VARCHAR); //nationality
        s.setInt(i++, 0); //organization id
        s.setNull(i++, java.sql.Types.BIGINT);
        s.setNull(i++, java.sql.Types.VARCHAR); //passport no
        s.setNull(i++, java.sql.Types.VARCHAR); //passport type
        s.setNull(i++, java.sql.Types.VARCHAR); //phone extension
        s.setNull(i++, java.sql.Types.BIGINT); //position code id
        s.setNull(i++, java.sql.Types.BIGINT); //position id
        s.setNull(i++, java.sql.Types.VARCHAR); //postal code
        s.setNull(i++, java.sql.Types.BIGINT); //reference person id
        s.setNull(i++, java.sql.Types.BIGINT); //salary bucket id
        s.setNull(i++, java.sql.Types.VARCHAR); //salary currency
        s.setNull(i++, java.sql.Types.TIMESTAMP); //salary effective date
        s.setNull(i++, java.sql.Types.TIMESTAMP); //salary end date
        s.setNull(i++, java.sql.Types.NUMERIC); //salary gross
        s.setNull(i++, java.sql.Types.NUMERIC); //salary net
        s.setNull(i++, java.sql.Types.VARCHAR); //spouse name
        s.setNull(i++, java.sql.Types.TIMESTAMP); //start date
        s.setString(i++, ""); //status
        s.setString(i++, o.lastName);
        setId(s, i++, o.userId);
        s.setObject(i++, UUID.randomUUID());
        if (!inserting) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<Employee> listBy(String userId) {
        return call(() -> query(createStatement(createQuerySql("", "", createWhereSql(userId)))));
    }

    @Override
    public List<DetailedEmployee> listDetailedBy(String userId) {
        String sql = createDetailedQuerySql(createWhereSql(userId));
        return call(() -> queryFor(createStatement(sql), this::toDetailedEmployee));
    }

    @Override
    public DetailedEmployee readDetailed(String id) {
        String sql = createDetailedQuerySql("e.id = " + id);
        return call(() -> queryFor(createStatement(sql), this::toDetailedEmployee)).get(0);
    }

    @Override
    public List<Employee> listSummarized() {
        String sql = createQuerySql("id, name, surname", "", "", "name", "");
        return call(() -> queryFor(createStatement(sql), this::toSummarizedEmployee));
    }

    private Employee toSummarizedEmployee(ResultSet s) throws SQLException {
        return new Employee().withId(getId(s, "id"))
                .withFirstName(s.getString("name"))
                .withLastName(s.getString("surname"));
    }

    private DetailedEmployee toDetailedEmployee(ResultSet s) throws SQLException {
        return new DetailedEmployee(
            toObject(s),
            new User(getId(s, "user_id"), s.getString("username"), null));
    }

    private String createDetailedQuerySql(String whereSql) {
        return createQuerySql(
            "e.*, u.user_name username",
            "opm_hrm_employee e JOIN opm_sys_user u ON e.user_id = u.id",
            whereSql);
    }

    private String createWhereSql(String userId) {
        return (userId == null)? "" : ("user_id = " + userId);
    }
}