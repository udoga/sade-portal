package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.model.Worklog;
import com.sadesoft.portal.core.repo.WorklogRepo;

import java.util.List;

public class WorklogService {
    private final WorklogRepo repo;

    public WorklogService(WorklogRepo repo) {
        this.repo = repo;
    }

    public List<Worklog> list() {
        return repo.list();
    }

    public List<Worklog> paginate(int pageNo, int pageSize) {
        return repo.paginate(pageNo, pageSize);
    }

    public Worklog read(String id) {
        return repo.read(id);
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
