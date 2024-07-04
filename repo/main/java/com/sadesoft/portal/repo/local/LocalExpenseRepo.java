package com.sadesoft.portal.repo.local;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.*;
import com.sadesoft.portal.core.repo.ExpenseRepo;

public class LocalExpenseRepo extends JdbcRepo<Expense> implements ExpenseRepo {
    public LocalExpenseRepo(Connection connection) {
        super(connection, "expenses", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("employee_id"),
                new JdbcColumn("form_date"),
                new JdbcColumn("description"),
                new JdbcColumn("approval_status")));
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
    protected void fillStatement(PreparedStatement s, Expense o) throws SQLException {
        int i = 1;
        setId(s, i++, o.employeeId);
        setLocalDate(s, i++, o.formDate);
        s.setString(i++, o.description);
        s.setString(i++, o.status);
        if (o.id != null) setId(s, i, o.id);
    }
}
