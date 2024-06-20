package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.repo.ProjectRepo;

public class MockProjectRepo extends MockRepo<Project> implements ProjectRepo {
    public MockProjectRepo() {
        super("Client");
    }
}
