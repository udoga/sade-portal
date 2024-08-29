package com.sadesoft.portal.core.mock;

import java.util.List;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.query.ReceiptQuery;
import com.sadesoft.portal.core.model.Receipt;
import com.sadesoft.portal.core.repo.ReceiptRepo;

public class MockReceiptRepo extends MockRepo<Receipt> implements ReceiptRepo {
    public MockReceiptRepo() {
        super("Receipt");
    }

    @Override
    public List<Receipt> listBy(ReceiptQuery query) {
        return records.stream().filter(r -> r.matches(query)).toList();
    }
}
