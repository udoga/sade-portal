package com.sadesoft.portal.core.repo;

import java.util.List;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.model.Role;

public interface RoleRepo extends Repo<Role> {
    List<Role> listSummarized();
}
