package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Grant;
import com.sadesoft.portal.core.repo.GrantRepo;

public class MockGrantRepo extends MockRepo<Grant> implements GrantRepo {
    public MockGrantRepo() {
        super("Grant");
    }
}
