package com.sadesoft.portal.core.repo;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.compound.AssignmentWithRole;
import com.sadesoft.portal.core.model.Assignment;

import java.util.List;

public interface AssignmentRepo extends Repo<Assignment> {
    List<AssignmentWithRole> listWithRole();
    List<AssignmentWithRole> listWithRoleByUserId(String userId);
}
