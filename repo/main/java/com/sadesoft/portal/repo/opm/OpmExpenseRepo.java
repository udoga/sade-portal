package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.ApprovalStatus;
import com.sadesoft.portal.core.model.Currency;
import com.sadesoft.portal.core.model.Expense;
import com.sadesoft.portal.core.repo.ExpenseRepo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OpmExpenseRepo extends JdbcRepo<Expense> implements ExpenseRepo {
    public OpmExpenseRepo(Connection connection) {
        super(connection, "opm_acc_expense", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("employee_id"),
                new JdbcColumn("expense_date"),
                new JdbcColumn("description")));
    }

    @Override
    public Expense toObject(ResultSet s) throws SQLException {
        return new Expense(
                getId(s, "id"),
                getId(s, "employee_id"),
                getLocalDateTime(s, "expense_date").toLocalDate(),
                null,
                null,
                null,
                s.getString("description"),
                null);
    }

    @Override
    protected void fillStatement(PreparedStatement s, Expense o) throws SQLException {
        int i = 1;
        setId(s, i++, o.employeeId);
        setLocalDateTime(s, i++, o.formDate.atTime(0, 0, 0));
        s.setString(i++, o.description);
        if (o.id != null) setId(s, i, o.id);
    }
}
