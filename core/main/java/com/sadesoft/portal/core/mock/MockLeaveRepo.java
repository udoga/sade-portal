package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Leave;
import com.sadesoft.portal.core.repo.LeaveRepo;

public class MockLeaveRepo extends MockRepo<Leave> implements LeaveRepo {
    public MockLeaveRepo() {
        super("Leave");
    }
}
