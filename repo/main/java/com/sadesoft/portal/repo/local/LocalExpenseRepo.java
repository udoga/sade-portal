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
                new JdbcColumn("person_id"),
                new JdbcColumn("form_date"),
                new JdbcColumn("amount"),
                new JdbcColumn("currency", "currency"),
                new JdbcColumn("paid"),
                new JdbcColumn("description"),
                new JdbcColumn("approval_status", "approval_status")));
    }

    @Override
    public Expense toObject(ResultSet s) throws SQLException {
        int i = 0;
        return new Expense(
                getId(s, getColumnName(i++)),
                getId(s, getColumnName(i++)),
                getLocalDate(s, getColumnName(i++)),
                s.getBigDecimal(getColumnName(i++)),
                Currency.valueOf(s.getString(getColumnName(i++))),
                s.getBoolean(getColumnName(i++)),
                s.getString(getColumnName(i++)),
                ApprovalStatus.valueOf(s.getString(getColumnName(i)))
        );
    }

    @Override
    protected void fillStatement(PreparedStatement s, Expense o) throws SQLException {
        int i = 1;
        setId(s, i++, o.personId);
        setLocalDate(s, i++, o.formDate);
        s.setBigDecimal(i++, o.amount);
        s.setString(i++, o.currency.name());
        s.setBoolean(i++, o.paid);
        s.setString(i++, o.description);
        s.setString(i++, o.status.name());
        if (o.id != null) setId(s, i, o.id);
    }
}
