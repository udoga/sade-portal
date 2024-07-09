package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.VacationCategory;
import com.sadesoft.portal.core.repo.VacationCategoryRepo;

public class MockVacationCategoryRepo extends MockRepo<VacationCategory> implements VacationCategoryRepo {
    public MockVacationCategoryRepo() {
        super("VacationCategory");
    }
}
