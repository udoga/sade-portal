package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Session;
import com.sadesoft.portal.core.repo.SessionRepo;

public class MockSessionRepo extends MockRepo<Session> implements SessionRepo {
    public MockSessionRepo() {
        super("Session");
    }
}
