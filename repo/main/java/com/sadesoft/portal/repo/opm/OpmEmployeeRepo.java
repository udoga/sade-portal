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
    protected void fillStatement(PreparedStatement s, Employee o) throws SQLException {
        int i = 1;
        boolean inserting = (o.id == null);
        s.setString(i++, ""); //address
        s.setString(i++, ""); //bank name
        s.setString(i++, ""); //birth place
        setLocalDateTime(s, i++, LocalDateTime.now()); //birthday
        s.setString(i++, ""); //blood type
        s.setInt(i++, 1); //branch id
        s.setString(i++, ""); //city
        s.setString(i++, "TR"); //country
        if (inserting) s.setInt(i++, 1); //created by
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now()); //created time
        s.setString(i++, ""); //disabled description
        s.setInt(i++, 1); //disabled rate
        s.setString(i++, "N"); //disabled status
        s.setString(i++, ""); //driving license
        s.setString(i++, "personal@email.com"); //email personal
        s.setString(i++, "work@email.com"); //email work
        s.setString(i++, "1"); //employee code
        s.setInt(i++, 1); //employee type id
        s.setString(i++, ""); //father name
        s.setString(i++, ""); //first aid card
        setLocalDateTime(s, i++, LocalDateTime.now()); //first aid card expiration date
        setLocalDateTime(s, i++, LocalDateTime.now());  //first employment date
        s.setInt(i++, 1); //food card first month amount
        s.setString(i++, ""); //food card type5
        setLocalDateTime(s, i++, LocalDateTime.now()); //fuel right end date
        s.setString(i++, ""); //function
        s.setString(i++, "N"); //gender
        s.setString(i++, "");//iban
        s.setString(i++, "");//identity card no
        s.setString(i++, "");//identity card serial no
        setLocalDateTime(s, i++, LocalDateTime.now()); //identity card validity date
        s.setInt(i++, 1); //last updated by
        setLocalDateTime(s, i++, LocalDateTime.now()); //last updated time
        setLocalDateTime(s, i++, LocalDateTime.now()); //leave date
        s.setString(i++, ""); //leave description
        s.setInt(i++, 1); //level id
        s.setString(i++, ""); //maiden name
        s.setInt(i++, 1); //manager id
        s.setString(i++, ""); //marital status
        setLocalDateTime(s, i++, LocalDateTime.now()); //marriage date
        s.setString(i++, ""); //military description
        setLocalDateTime(s, i++, LocalDateTime.now()); //military discharge date
        s.setString(i++, ""); //military status
        s.setString(i++, ""); //mobile personal
        s.setString(i++, ""); //mobile work
        s.setString(i++, ""); //mother name
        s.setString(i++, o.firstName);
        s.setString(i++, "TR");
        s.setInt(i++, 1); //organization id
        s.setInt(i++, 1); //organization node id
        s.setString(i++, ""); //passport no
        s.setString(i++, ""); //passport type
        s.setString(i++, ""); //phone extension
        s.setInt(i++, 1); //position code id
        s.setInt(i++, 1); //position id
        s.setString(i++, ""); //postal code
        s.setInt(i++, 1); //reference person id
        s.setInt(i++, 1); //salary bucket id
        s.setString(i++, "TRY"); //salary currency
        setLocalDateTime(s, i++, LocalDateTime.now()); //salary effective date
        setLocalDateTime(s, i++, LocalDateTime.now()); //salary end date
        s.setInt(i++, 0); //salary gross
        s.setInt(i++, 0); //salary net
        s.setString(i++, ""); //spouse name
        setLocalDateTime(s, i++, LocalDateTime.now()); //start date
        s.setString(i++, "A"); //status
        s.setString(i++, o.lastName);
        setId(s, i++, o.userId);
        s.setObject(i++, UUID.randomUUID());
        if (!inserting) setId(s, i, o.id);
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