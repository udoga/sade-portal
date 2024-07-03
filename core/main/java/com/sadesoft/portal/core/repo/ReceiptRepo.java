package com.sadesoft.portal.core.repo;

import java.util.List;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.model.Receipt;

public interface ReceiptRepo extends Repo<Receipt> {
    List<Receipt> listByExpenseId(String expenseId);
}
