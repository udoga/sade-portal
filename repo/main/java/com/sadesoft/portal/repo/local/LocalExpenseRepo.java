package com.sadesoft.portal.repo.local;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedExpense;
import com.sadesoft.portal.core.filter.ExpenseFilter;
import com.sadesoft.portal.core.model.*;
import com.sadesoft.portal.core.repo.ExpenseRepo;

public class LocalExpenseRepo extends JdbcRepo<Expense> implements ExpenseRepo {
    public LocalExpenseRepo(Connection connection) {
        super(connection, "expenses", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("employee_id", true),
                new JdbcColumn("form_date", true),
                new JdbcColumn("description", true),
                new JdbcColumn("approval_status", true)));
    }

    @Override
    public Expense toObject(ResultSet s) throws SQLException {
        return new Expense(
                getId(s, "id"),
                getId(s, "employee_id"),
                getLocalDate(s, "form_date"),
                s.getString("description"),
                s.getString("approval_status"));
    }

    @Override
    protected PreparedStatement fillStatement(PreparedStatement s, Expense o) throws SQLException {
        int i = 1;
        setId(s, i++, o.employeeId);
        setLocalDate(s, i++, o.formDate);
        s.setString(i++, o.description);
        s.setString(i++, o.status);
        if (o.id != null) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<Expense> listBy(ExpenseFilter filter) {
        return call(() -> query(createStatement(createQuerySql("", "", createWhereSql(filter)))));
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
            "e.*, m.first_name, m.last_name",
            "expenses e JOIN employees m ON e.employee_id = m.id",
            whereSql);
    }

    private String createWhereSql(ExpenseFilter f) {
        List<String> conditions = new ArrayList<>();
        if (f.employeeId != null) conditions.add("e.employee_id = " + f.employeeId);
        return String.join(" and ", conditions);
    }
}
