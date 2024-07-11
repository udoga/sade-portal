package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedExpense;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.Expense;
import com.sadesoft.portal.core.repo.ExpenseRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OpmExpenseRepo extends JdbcRepo<Expense> implements ExpenseRepo {
    private final String selectWithEmployeeSql =
        "SELECT e.*, " +
        "m.name first_name, m.surname last_name " +
        "FROM opm_acc_expense e JOIN opm_hrm_employee m ON e.employee_id = m.id";

    public OpmExpenseRepo(Connection connection) {
        super(connection, "opm_acc_expense", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("employee_id"),
                new JdbcColumn("expense_date"),
                new JdbcColumn("description"),
                new JdbcColumn("expense_status")));
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
    protected void fillStatement(PreparedStatement s, Expense o) throws SQLException {
        int i = 1;
        setId(s, i++, o.employeeId);
        setLocalDateTime(s, i++, o.formDate.atTime(0, 0, 0));
        s.setString(i++, o.description);
        s.setString(i++, o.status);
        if (o.id != null) setId(s, i, o.id);
    }

    @Override
    public List<DetailedExpense> listWithEmployee() {
        return call(() -> queryFor(createStatement(selectWithEmployeeSql), this::toDetailedExpense));
    }

    public DetailedExpense toDetailedExpense(ResultSet s) throws SQLException {
    return new DetailedExpense(
            toObject(s),
            new Employee(getId(s, "employee_id"), null, s.getString("first_name"), s.getString("last_name")));
    }
}
