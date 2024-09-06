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
import com.sadesoft.portal.core.query.ExpenseQuery;
import com.sadesoft.portal.core.model.*;
import com.sadesoft.portal.core.repo.ExpenseRepo;

public class LocalExpenseRepo extends JdbcRepo<Expense> implements ExpenseRepo {
    public LocalExpenseRepo(Connection connection) {
        super(connection, "expenses", "e", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("employee_id", true),
                new JdbcColumn("form_date", true),
                new JdbcColumn("description", true),
                new JdbcColumn("approval_status", true)));
    }

    @Override
    protected Expense toObject(ResultSet s) throws SQLException {
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
    public List<Expense> listBy(ExpenseQuery q) {
        String sql = getQuerySql() + getWhereSql(q) + getOrderSql("id") + getLimitSql();
        return call(() -> query(createStatement(sql)));
    }

    @Override
    public List<DetailedExpense> listDetailedBy(ExpenseQuery q) {
        String sql = getDetailedQuerySql() + getWhereSql(q) + getOrderSql("id") + getLimitSql();
        return call(() -> queryFor(createStatement(sql), this::toDetailedExpense));
    }

    @Override
    public DetailedExpense readDetailed(String id) {
        String sql = getDetailedQuerySql() + getWhereSql("e.id = " + id);
        return call(() -> queryFor(createStatement(sql), this::toDetailedExpense)).get(0);
    }

    private DetailedExpense toDetailedExpense(ResultSet s) throws SQLException {
        return new DetailedExpense(
            toObject(s),
            new Employee(getId(s, "employee_id"), null, s.getString("first_name"), s.getString("last_name")));
    }

    private String getDetailedQuerySql() {
        return getQuerySql(
            "e.*, m.first_name, m.last_name",
            "expenses e" +
                " left join employees m on e.employee_id = m.id");
    }

    private String getWhereSql(ExpenseQuery q) {
        List<String> conditions = new ArrayList<>();
        if (q.employeeId != null) conditions.add("e.employee_id = " + q.employeeId);
        return getWhereSql(String.join(" and ", conditions));
    }
}
