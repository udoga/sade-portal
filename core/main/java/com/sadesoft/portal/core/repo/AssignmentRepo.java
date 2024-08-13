package com.sadesoft.portal.core.repo;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.detailed.DetailedAssignment;
import com.sadesoft.portal.core.filter.AssignmentFilter;
import com.sadesoft.portal.core.model.Assignment;

import java.util.List;

public interface AssignmentRepo extends Repo<Assignment> {
    List<Assignment> listBy(AssignmentFilter filter);
    List<DetailedAssignment> listDetailedBy(AssignmentFilter filter);
}
