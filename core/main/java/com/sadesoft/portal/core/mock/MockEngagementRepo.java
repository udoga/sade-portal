package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Engagement;
import com.sadesoft.portal.core.repo.EngagementRepo;

public class MockEngagementRepo extends MockRepo<Engagement> implements EngagementRepo {
    public MockEngagementRepo() {
        super("Engagement");
    }
}