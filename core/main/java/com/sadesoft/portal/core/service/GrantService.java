package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.detailed.DetailedGrant;
import com.sadesoft.portal.core.query.GrantQuery;
import com.sadesoft.portal.core.model.Grant;
import com.sadesoft.portal.core.repo.GrantRepo;

import java.util.List;

public class GrantService {
    private final GrantRepo repo;

    public GrantService(GrantRepo repo) {
        this.repo = repo;
    }

    public List<Grant> listBy(GrantQuery query) {
        return repo.listBy(query);
    }

    public List<DetailedGrant> listDetailedBy(GrantQuery query) {
        return repo.listDetailedBy(query);
    }

    public Grant create(Grant grant) {
        return repo.create(grant);
    }

    public Grant delete(String id) {
        return repo.delete(id);
    }
}
