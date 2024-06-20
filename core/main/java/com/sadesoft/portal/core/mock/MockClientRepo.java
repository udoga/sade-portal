package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Client;
import com.sadesoft.portal.core.repo.ClientRepo;

public class MockClientRepo extends MockRepo<Client> implements ClientRepo {
    public MockClientRepo() {
        super("Client");
    }
}
