package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.ReceiptCategory;
import com.sadesoft.portal.core.repo.ReceiptCategoryRepo;

public class MockReceiptCategoryRepo extends MockRepo<ReceiptCategory> implements ReceiptCategoryRepo {
    public MockReceiptCategoryRepo() {
        super("ReceiptCategory");
    }
}
