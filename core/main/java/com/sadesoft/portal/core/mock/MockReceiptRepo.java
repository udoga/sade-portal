package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Receipt;
import com.sadesoft.portal.core.repo.ReceiptRepo;

public class MockReceiptRepo extends MockRepo<Receipt> implements ReceiptRepo {
    public MockReceiptRepo() {
        super("Receipt");
    }
}
