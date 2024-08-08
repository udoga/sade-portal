package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.detailed.DetailedWorklog;
import com.sadesoft.portal.core.filter.WorklogFilter;
import com.sadesoft.portal.core.model.Worklog;
import com.sadesoft.portal.core.repo.WorklogRepo;

import java.util.List;

public class WorklogService {
    private final WorklogRepo repo;

    public WorklogService(WorklogRepo repo) {
        this.repo = repo;
    }

    public List<Worklog> listBy(WorklogFilter filter) {
        return repo.listBy(filter);
    }

    public List<DetailedWorklog> listDetailedBy(WorklogFilter filter) {
        return repo.listDetailedBy(filter);
    }

    public Worklog read(String id) {
        return repo.read(id);
    }

    public DetailedWorklog readDetailed(String id) {
        return repo.readDetailed(id);
    }

    public Worklog create(Worklog worklog) {
        return repo.create(worklog);
    }

    public Worklog update(String id, Worklog worklog) {
        return repo.update(id, worklog);
    }

    public Worklog delete(String id) {
        return repo.delete(id);
    }
}
