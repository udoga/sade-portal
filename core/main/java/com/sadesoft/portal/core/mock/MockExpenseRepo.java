package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.ListRepo;
import com.sadesoft.portal.core.model.Expense;
import com.sadesoft.portal.core.repo.ExpenseRepo;

public class MockExpenseRepo extends ListRepo<Expense> implements ExpenseRepo {
    public MockExpenseRepo() {
        super("Expense");
    }
}
