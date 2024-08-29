package com.sadesoft.portal.core.repo;

import java.util.List;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.detailed.DetailedExpense;
import com.sadesoft.portal.core.query.ExpenseQuery;
import com.sadesoft.portal.core.model.Expense;

public interface ExpenseRepo extends Repo<Expense> {
    DetailedExpense readDetailed(String id);
    List<DetailedExpense> listDetailedBy(ExpenseQuery query);
    List<Expense> listBy(ExpenseQuery query);
}
