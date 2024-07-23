package com.sadesoft.portal.core.mock;

import java.util.List;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Receipt;
import com.sadesoft.portal.core.repo.ReceiptRepo;

public class MockReceiptRepo extends MockRepo<Receipt> implements ReceiptRepo {
    public MockReceiptRepo() {
        super("Receipt");
    }

    @Override
    public List<Receipt> listBy(String expenseId) {
        return records.stream().filter(r -> r.matches(expenseId)).toList();
    }
}
