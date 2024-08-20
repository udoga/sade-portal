package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedExpense;
import com.sadesoft.portal.core.filter.ExpenseFilter;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.Expense;
import com.sadesoft.portal.core.repo.ExpenseRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OpmExpenseRepo extends JdbcRepo<Expense> implements ExpenseRepo {
    public OpmExpenseRepo(Connection connection) {
        super(connection, "opm_acc_expense", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("business_card"),
                new JdbcColumn("created_by"),
                new JdbcColumn("created_time"),
                new JdbcColumn("description", true),
                new JdbcColumn("employee_id", true),
                new JdbcColumn("expense_date", true),
                new JdbcColumn("expense_status", true),
                new JdbcColumn("last_updated_by"),
                new JdbcColumn("last_updated_time"),
                new JdbcColumn("organization_id"),
                new JdbcColumn("project_id"),
                new JdbcColumn("status"),
                new JdbcColumn("uuid")));
    }

    @Override
    public Expense toObject(ResultSet s) throws SQLException {
        return new Expense(
                getId(s, "id"),
                getId(s, "employee_id"),
                getLocalDateTime(s, "expense_date").toLocalDate(),
                s.getString("description"),
                s.getString("expense_status"));
    }

    @Override
    protected PreparedStatement fillStatement(PreparedStatement s, Expense o) throws SQLException {
        int i = 1;
        boolean inserting = (o.id == null);
        if (inserting) s.setBoolean(i++, false);
        if (inserting) s.setInt(i++, 0);
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now());
        s.setString(i++, o.description);
        setId(s, i++, o.employeeId);
        setLocalDateTime(s, i++, o.formDate.atTime(0, 0, 0));
        s.setString(i++, o.status);
        if (inserting) s.setInt(i++, 0);
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now());
        if (inserting) s.setInt(i++, 0);
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT);
        if (inserting) s.setString(i++, "");
        if (inserting) s.setObject(i++, UUID.randomUUID());
        if (!inserting) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<Expense> listBy(ExpenseFilter filter) {
        return call(() -> query(createStatement(createQuerySql("", tableName + " e", createWhereSql(filter)))));
    }

    @Override
    public List<DetailedExpense> listDetailedBy(ExpenseFilter filter) {
        String sql = createDetailedQuerySql(createWhereSql(filter));
        return call(() -> queryFor(createStatement(sql), this::toDetailedExpense));
    }

    @Override
    public DetailedExpense readDetailed(String id) {
        String sql = createDetailedQuerySql("e.id = " + id);
        return call(() -> queryFor(createStatement(sql), this::toDetailedExpense)).get(0);
    }

    public DetailedExpense toDetailedExpense(ResultSet s) throws SQLException {
        return new DetailedExpense(
            toObject(s),
            new Employee(getId(s, "employee_id"), null, s.getString("first_name"), s.getString("last_name")));
    }

    private String createDetailedQuerySql(String whereSql) {
        return createQuerySql(
            "e.*, m.name first_name, m.surname last_name",
            "opm_acc_expense e JOIN opm_hrm_employee m ON e.employee_id = m.id",
            whereSql);
    }

    private String createWhereSql(ExpenseFilter f) {
        List<String> conditions = new ArrayList<>();
        if (f.employeeId != null) conditions.add("e.employee_id = " + f.employeeId);
        return String.join(" and ", conditions);
    }
}
