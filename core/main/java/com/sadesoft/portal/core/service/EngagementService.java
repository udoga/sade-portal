package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.detailed.DetailedEngagement;
import com.sadesoft.portal.core.query.EngagementQuery;
import com.sadesoft.portal.core.model.Engagement;
import com.sadesoft.portal.core.repo.EngagementRepo;

import java.util.List;

public class EngagementService {
    private final EngagementRepo repo;

    public EngagementService(EngagementRepo repo) {
        this.repo = repo;
    }

    public List<Engagement> listBy(EngagementQuery query) {
        return repo.listBy(query);
    }

    public List<DetailedEngagement> listDetailedBy(EngagementQuery query) {
        return repo.listDetailedBy(query);
    }

    public Engagement read(String id) {
        return repo.read(id);
    }

    public Engagement create(Engagement engagement) {
        return repo.create(engagement);
    }

    public Engagement update(String id, Engagement engagement) {
        return repo.update(id, engagement);
    }

    public Engagement delete(String id) {
        return repo.delete(id);
    }
}