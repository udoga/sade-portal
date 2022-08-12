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

    public List<Project> listPage(int pageNo, int pageSize) {
        return repo.listPage(pageNo, pageSize);
    }

    public Project read(String id) {
        return repo.read(id);
    }

    public Project create(Project project) {
        return repo.create(project);
    }

    public Project update(String id, Project project) {
        return repo.update(id, project);
    }

    public Project delete(String id) {
        return repo.delete(id);
    }
}
