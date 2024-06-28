package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.model.Grant;
import com.sadesoft.portal.core.repo.GrantRepo;

import java.util.List;

public class GrantService {
    private final GrantRepo repo;

    public GrantService(GrantRepo repo) {
        this.repo = repo;
    }

    public List<Grant> list() {
        return repo.list();
    }

    public Grant create(Grant grant) {
        return repo.create(grant);
    }

    public Grant delete(String id) {
        return repo.delete(id);
    }
}
