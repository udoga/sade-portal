package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedEmployee;
import com.sadesoft.portal.core.query.EmployeeQuery;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.repo.EmployeeRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OpmEmployeeRepo extends JdbcRepo<Employee> implements EmployeeRepo {
    public OpmEmployeeRepo(Connection connection) {
        super(connection, "opm_hrm_employee", "e", List.of(
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
                new JdbcColumn("name", true),
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
                new JdbcColumn("surname", true),
                new JdbcColumn("user_id", true),
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
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //address
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //bank name
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //birth place
        if (inserting) s.setNull(i++, java.sql.Types.TIMESTAMP);//birthday
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //blood type
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT); //branch id
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //city
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //country
        if (inserting) s.setInt(i++, 0); //created by
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now()); //created time
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //disabled description
        if (inserting) s.setNull(i++, java.sql.Types.INTEGER); //disabled rate
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //disabled status
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //driving license
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //email personal
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //email work
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //employee code
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT); //employee type id
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //father name
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //first aid card
        if (inserting) s.setNull(i++, java.sql.Types.TIMESTAMP); //first aid card expiration date
        if (inserting) s.setNull(i++, java.sql.Types.TIMESTAMP);  //first employment date
        if (inserting) s.setNull(i++, java.sql.Types.NUMERIC); //food card first month amount
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //food card type5
        if (inserting) s.setNull(i++, java.sql.Types.TIMESTAMP); //fuel right end date
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //function
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //gender
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR);//iban
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR);//identity card no
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR);//identity card serial no
        if (inserting) s.setNull(i++, java.sql.Types.TIMESTAMP); //identity card validity date
        if (inserting) s.setInt(i++, 0); //last updated by
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now()); //last updated time
        if (inserting) s.setNull(i++, java.sql.Types.TIMESTAMP); //leave date
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //leave description
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT); //level id
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //maiden name
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT); //manager id
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //marital status
        if (inserting) s.setNull(i++, java.sql.Types.TIMESTAMP); //marriage date
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //military description
        if (inserting) s.setNull(i++, java.sql.Types.TIMESTAMP); //military discharge date
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //military status
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //mobile personal
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //mobile work
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //mother name
        s.setString(i++, o.firstName);
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //nationality
        if (inserting) s.setInt(i++, 0); //organization id
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT);
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //passport no
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //passport type
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //phone extension
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT); //position code id
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT); //position id
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //postal code
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT); //reference person id
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT); //salary bucket id
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //salary currency
        if (inserting) s.setNull(i++, java.sql.Types.TIMESTAMP); //salary effective date
        if (inserting) s.setNull(i++, java.sql.Types.TIMESTAMP); //salary end date
        if (inserting) s.setNull(i++, java.sql.Types.NUMERIC); //salary gross
        if (inserting) s.setNull(i++, java.sql.Types.NUMERIC); //salary net
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR); //spouse name
        if (inserting) s.setNull(i++, java.sql.Types.TIMESTAMP); //start date
        if (inserting) s.setString(i++, ""); //status
        s.setString(i++, o.lastName);
        setId(s, i++, o.userId);
        if (inserting) s.setObject(i++, UUID.randomUUID());
        if (!inserting) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<Employee> listBy(EmployeeQuery q) {
        String sql = getQuerySql() + getWhereSql(q) + getOrderSql("id") + getLimitSql() + getOffsetSql(q.offset);
        return call(() -> query(createStatement(sql)));
    }

    @Override
    public List<DetailedEmployee> listDetailedBy(EmployeeQuery q) {
        String sql = getDetailedQuerySql() + getWhereSql(q) + getOrderSql("id") + getLimitSql() + getOffsetSql(q.offset);
        return call(() -> queryFor(createStatement(sql), this::toDetailedEmployee));
    }

    @Override
    public DetailedEmployee readDetailed(String id) {
        String sql = getDetailedQuerySql() + getWhereSql("e.id = " + id);
        return call(() -> queryFor(createStatement(sql), this::toDetailedEmployee)).get(0);
    }

    @Override
    public List<Employee> listOptions() {
        String sql = getQuerySql("id, name, surname") + getOrderSql("name");
        return call(() -> queryFor(createStatement(sql), this::toEmployeeOption));
    }

    private Employee toEmployeeOption(ResultSet s) throws SQLException {
        return new Employee().withId(getId(s, "id"))
                .withFirstName(s.getString("name"))
                .withLastName(s.getString("surname"));
    }

    private DetailedEmployee toDetailedEmployee(ResultSet s) throws SQLException {
        return new DetailedEmployee(
            toObject(s),
            new User(getId(s, "user_id"), s.getString("username"), null));
    }

    private String getDetailedQuerySql() {
        return getQuerySql(
            "e.*, u.user_name username",
            tableName + " " + tableAlias +
                " left join opm_sys_user u on e.user_id = u.id");
    }

    private String getWhereSql(EmployeeQuery q) {
        List<String> conditions = new ArrayList<>();
        if (q.userId != null) conditions.add("e.user_id = " + q.userId);
        return getWhereSql(String.join(" and ", conditions));
    }
}
