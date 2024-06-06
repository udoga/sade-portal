package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.ApprovalStatus;
import com.sadesoft.portal.core.model.Leave;
import com.sadesoft.portal.core.repo.LeaveRepo;

import java.util.List;
import java.util.Objects;

public class MockLeaveRepo extends MockRepo<Leave> implements LeaveRepo {
    public MockLeaveRepo() {
        super("Leave");
    }

    @Override
    public List<Leave> paginateByStatus(int pageNo, int pageSize, ApprovalStatus status) {
        if (status != null) records.removeIf(r -> !Objects.equals(r.status.toString(), status.toString()));
        return paginate(pageNo, pageSize);
    }
}
