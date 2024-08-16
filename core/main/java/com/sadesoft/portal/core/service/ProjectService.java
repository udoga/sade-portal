package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.detailed.DetailedProject;
import com.sadesoft.portal.core.filter.ProjectFilter;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.repo.ProjectRepo;

import java.util.List;

public class ProjectService {
    private final ProjectRepo repo;

    public ProjectService(ProjectRepo repo) {
        this.repo = repo;
    }

    public List<Project> list() {
        return repo.list();
    }

    public List<Project> listSummarized() {
        return repo.listSummarized();
    }

    public List<Project> listBy(ProjectFilter filter) {
        return repo.listBy(filter);
    }

    public List<DetailedProject> listDetailedBy(ProjectFilter filter) {
        return repo.listDetailedBy(filter);
    }

    public Project read(String id) {
        return repo.read(id);
    }

    public DetailedProject readDetailed(String id) {
        return repo.readDetailed(id);
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
