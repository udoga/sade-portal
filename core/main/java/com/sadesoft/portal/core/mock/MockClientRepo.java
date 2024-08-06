package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Client;
import com.sadesoft.portal.core.repo.ClientRepo;

import java.util.List;

public class MockClientRepo extends MockRepo<Client> implements ClientRepo {
    public MockClientRepo() {
        super("Client");
    }

    @Override
    public List<Client> listSummarized() {
        return list().stream().map(c -> new Client().withId(c.id).withName(c.name)).toList();
    }
}
