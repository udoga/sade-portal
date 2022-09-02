package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.ListRepo;
import com.sadesoft.portal.core.model.Receipt;
import com.sadesoft.portal.core.repo.ReceiptRepo;

public class MockReceiptRepo extends ListRepo<Receipt> implements ReceiptRepo {
    public MockReceiptRepo() {
        super("Receipt");
    }
}
