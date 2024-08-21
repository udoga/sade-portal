package com.sadesoft.portal.core.repo;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.model.Permission;

import java.util.List;

public interface PermissionRepo extends Repo<Permission> {
    List<Permission> listSummarized();
}
