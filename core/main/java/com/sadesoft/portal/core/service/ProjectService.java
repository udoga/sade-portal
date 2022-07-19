package com.sadesoft.portal.core.service;

import java.util.List;
import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.model.Project;

public class ProjectService {
    private final Repo<Project> repo;

    public ProjectService(Repo<Project> repo) {
        this.repo = repo;
    }

    public List<Project> list() {
        return repo.list();
    }

    public Project read(int id) {
        return repo.read(id);
    }

    public Project create(Project project) {
        return repo.create(project);
    }

    public Project update(int id, Project project) {
        return repo.update(id, project);
    }

    public Project delete(int id) {
        return repo.delete(id);
    }
}
