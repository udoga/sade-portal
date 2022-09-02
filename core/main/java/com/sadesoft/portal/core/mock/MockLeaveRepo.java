package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.ListRepo;
import com.sadesoft.portal.core.model.Leave;
import com.sadesoft.portal.core.repo.LeaveRepo;

public class MockLeaveRepo extends ListRepo<Leave> implements LeaveRepo {
    public MockLeaveRepo() {
        super("Leave");
    }
}
