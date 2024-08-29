package com.sadesoft.portal.core.mock;

import java.util.List;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.detailed.DetailedProject;
import com.sadesoft.portal.core.query.ProjectQuery;
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
    public List<Project> listBy(ProjectQuery query) {
        return applyOffset(records.stream().filter(e -> e.matches(query)).toList(), query.offset);
    }

    @Override
    public List<DetailedProject> listDetailedBy(ProjectQuery query) {
        return listBy(query).stream().map(p -> new DetailedProject(p, getClient(p.clientId))).toList();
    }

    @Override
    public DetailedProject readDetailed(String id) {
        return new DetailedProject(read(id), getClient(read(id).clientId));
    }

    @Override
    public List<Project> listSummarized() {
        return list().stream().map(c -> new Project().withId(c.id).withName(c.name)).toList();
    }

    private Client getClient(String clientId) {
        return (clientRepo == null)? null : clientRepo.read(clientId);
    }
}
