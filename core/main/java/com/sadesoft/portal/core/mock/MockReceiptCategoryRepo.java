package com.sadesoft.portal.core.mock;

import java.util.List;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.ReceiptCategory;
import com.sadesoft.portal.core.repo.ReceiptCategoryRepo;

public class MockReceiptCategoryRepo extends MockRepo<ReceiptCategory> implements ReceiptCategoryRepo {
    public MockReceiptCategoryRepo() {
        super("ReceiptCategory");
    }

    @Override
    public List<ReceiptCategory> listSummarized() {
        return list().stream().map(s -> new ReceiptCategory().withId(s.id).withName(s.name)).toList();
    }
}
