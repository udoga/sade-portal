package com.sadesoft.portal.core.repo;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.model.Assignment;

public interface AssignmentRepo extends Repo<Assignment> {
    Assignment deleteByValues(Assignment a);
}
