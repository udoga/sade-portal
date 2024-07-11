package com.sadesoft.portal.core.mock;

import java.util.List;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.detailed.DetailedExpense;
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
    public List<DetailedExpense> listDetailed() {
        return records.stream().map(e -> new DetailedExpense(e, getEmployee(e.employeeId))).toList();
    }

    private Employee getEmployee(String employeeId) {
        return (employeeRepo == null)? null : employeeRepo.read(employeeId);
    }
}
