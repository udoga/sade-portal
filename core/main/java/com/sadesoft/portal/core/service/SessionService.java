package com.sadesoft.portal.core.service;

import java.util.List;

import com.sadesoft.portal.core.model.Session;
import com.sadesoft.portal.core.repo.SessionRepo;

public class SessionService {
    private final SessionRepo repo;

    public SessionService(SessionRepo repo) {
        this.repo = repo;
    }

    public List<Session> list() {
        return repo.list();
    }

    public Session read(String id) {
        return repo.read(id);
    }
}
