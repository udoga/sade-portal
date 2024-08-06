package com.sadesoft.portal.core.mock;

import java.util.List;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.detailed.DetailedProject;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.model.Client;
import com.sadesoft.portal.core.repo.ProjectRepo;
import com.sadesoft.portal.core.repo.ClientRepo;

public class MockProjectRepo extends MockRepo<Project> implements ProjectRepo {
    private final ClientRepo clientRepo;

    public MockProjectRepo(ClientRepo clientRepo) {
        super("Project");
        this.clientRepo = clientRepo;
    }

    @Override
    public List<Project> listBy(String clientId) {
        return records.stream().filter(p -> p.matches(clientId)).toList();
    }

    @Override
    public List<DetailedProject> listDetailedBy(String clientId) {
        return listBy(clientId).stream().map(p -> new DetailedProject(p, getClient(p.clientId))).toList();
    }

    @Override
    public DetailedProject readDetailed(String id) {
        return new DetailedProject(read(id), getClient(read(id).clientId));
    }

    private Client getClient(String clientId) {
        return (clientRepo == null)? null : clientRepo.read(clientId);
    }
}
