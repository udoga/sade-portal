package com.sadesoft.portal.core.mock;

import java.util.List;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.detailed.DetailedExpense;
import com.sadesoft.portal.core.filter.ExpenseFilter;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.Expense;
import com.sadesoft.portal.core.repo.EmployeeRepo;
import com.sadesoft.portal.core.repo.ExpenseRepo;

public class MockExpenseRepo extends MockRepo<Expense> implements ExpenseRepo {
    private final EmployeeRepo employeeRepo;

    public MockExpenseRepo(EmployeeRepo employeeRepo) {
        super("Expense");
        this.employeeRepo = employeeRepo;
    }

    @Override
    public List<Expense> listBy(ExpenseFilter filter) {
        return records.stream().filter(e -> e.passes(filter)).toList();
    }

    @Override
    public List<DetailedExpense> listDetailedBy(ExpenseFilter filter) {
        return listBy(filter).stream().map(e -> new DetailedExpense(e, getEmployee(e.employeeId))).toList();
    }
    @Override
    public DetailedExpense readDetailed(String id) {
        Expense expense = read(id);
        return new DetailedExpense(expense, getEmployee(expense.employeeId));
    }

    private Employee getEmployee(String employeeId) {
        return (employeeRepo == null)? null : employeeRepo.read(employeeId);
    }
}
