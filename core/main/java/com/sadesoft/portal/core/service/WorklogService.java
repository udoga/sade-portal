package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.detailed.DetailedWorklog;
import com.sadesoft.portal.core.query.WorklogQuery;
import com.sadesoft.portal.core.model.Worklog;
import com.sadesoft.portal.core.repo.WorklogRepo;
import com.sadesoft.portal.core.order.WorklogOrder;

import java.util.List;

public class WorklogService {
    private final WorklogRepo repo;

    public WorklogService(WorklogRepo repo) {
        this.repo = repo;
    }

    public List<Worklog> listBy(WorklogQuery query, WorklogOrder order) {
        return repo.listBy(query, order);
    }

    public List<DetailedWorklog> listDetailedBy(WorklogQuery query, WorklogOrder order) {
        return repo.listDetailedBy(query, order);
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
