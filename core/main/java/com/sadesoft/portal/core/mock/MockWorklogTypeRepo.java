package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.WorklogType;
import com.sadesoft.portal.core.repo.WorklogTypeRepo;

public class MockWorklogTypeRepo extends MockRepo<WorklogType> implements WorklogTypeRepo {
    public MockWorklogTypeRepo() {
        super("WorklogType");
    }
}
