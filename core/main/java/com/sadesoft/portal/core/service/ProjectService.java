package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.detailed.DetailedProject;
import com.sadesoft.portal.core.query.ProjectQuery;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.repo.ProjectRepo;

import java.util.List;
import java.util.regex.Pattern;

public class ProjectService {
    private final ProjectRepo repo;

    public ProjectService(ProjectRepo repo) {
        this.repo = repo;
    }

    public List<Project> listBy(ProjectQuery query) {
        return repo.listBy(query);
    }

    public List<DetailedProject> listDetailedBy(ProjectQuery query) {
        return repo.listDetailedBy(query);
    }

    public List<Project> listOptions() {
        return repo.listOptions();
    }

    public Project read(String id) {
        return repo.read(id);
    }

    public DetailedProject readDetailed(String id) {
        return repo.readDetailed(id);
    }

    public Project create(Project project) {
        check(project);
        return repo.create(project);
    }

    public Project update(String id, Project project) {
        check(project);
        return repo.update(id, project);
    }

    public Project delete(String id) {
        return repo.delete(id);
    }

    private void check(Project project) {
        checkMissingFields(project);
        checkFieldFormats(project);
    }

    private void checkMissingFields(Project project) {
        checkMissingField(project.clientId, "clientId");
        checkMissingField(project.name, "name");
        checkMissingField(project.description, "description");
    }

    private void checkFieldFormats(Project project) {
        checkFieldFormat(project.clientId, "clientId", "^[0-9]+$");
        checkFieldFormat(project.name, "name", "^[a-zA-Z]+( [a-zA-Z]+)*$");
    }

    private void checkMissingField(String value, String field) {
        if (value == null) throw new MissingFieldError(field);
    }

    private void checkFieldFormat(String value, String field, String regex) {
        if (!Pattern.matches(regex, value)) throw new InvalidInputError("Format of field '" + field + "' is invalid.");
    }
}
