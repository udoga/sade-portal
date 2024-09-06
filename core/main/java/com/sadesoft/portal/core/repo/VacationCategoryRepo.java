package com.sadesoft.portal.core.repo;

import java.util.List;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.model.VacationCategory;

public interface VacationCategoryRepo extends Repo<VacationCategory> {
    List<VacationCategory> listOptions();
}
