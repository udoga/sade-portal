package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Worklog;
import com.sadesoft.portal.core.repo.WorklogRepo;

public class MockWorklogRepo extends MockRepo<Worklog> implements WorklogRepo {
    public MockWorklogRepo() {
        super("Worklog");
    }
}
