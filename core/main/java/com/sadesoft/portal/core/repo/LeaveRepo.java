package com.sadesoft.portal.core.repo;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.model.ApprovalStatus;
import com.sadesoft.portal.core.model.Leave;

import java.util.List;

public interface LeaveRepo extends Repo<Leave> {
    List<Leave> listFiltered(ApprovalStatus status, int pageNo, int pageSize);
}
