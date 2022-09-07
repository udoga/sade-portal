package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Expense;
import com.sadesoft.portal.core.repo.ExpenseRepo;

public class MockExpenseRepo extends MockRepo<Expense> implements ExpenseRepo {
    public MockExpenseRepo() {
        super("Expense");
    }
}
