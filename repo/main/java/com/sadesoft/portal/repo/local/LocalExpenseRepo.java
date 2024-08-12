package com.sadesoft.portal.repo.local;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedExpense;
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
    public List<DetailedExpense> listDetailed() {
        String sql = createDetailedQuerySql();
        return call(() -> queryFor(createStatement(sql), this::toDetailedExpense));
    }

    @Override
    public DetailedExpense readDetailed(String id) {
        String sql = createDetailedQuerySqlWithId(id);
        return call(() -> queryFor(createStatement(sql), this::toDetailedExpense)).get(0);
    }

    public DetailedExpense toDetailedExpense(ResultSet s) throws SQLException {
        return new DetailedExpense(
            toObject(s),
            new Employee(getId(s, "employee_id"), null, s.getString("first_name"), s.getString("last_name")));
    }

    private String createDetailedQuerySql() {
        return createDetailedQuerySqlWithId("");
    }

    private String createDetailedQuerySqlWithId(String id) {
        return createQuerySql(
            "e.*, m.first_name, m.last_name",
            "expenses e JOIN employees m ON e.employee_id = m.id",
            id.isEmpty()? "" : (" e.id = " + id));
    }
}
