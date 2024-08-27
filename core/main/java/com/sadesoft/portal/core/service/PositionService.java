package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.model.Position;
import com.sadesoft.portal.core.repo.PositionRepo;

import java.util.List;

public class PositionService {
    private final PositionRepo repo;

    public PositionService(PositionRepo repo) {
        this.repo = repo;
    }

    public List<Position> list() {
        return repo.list();
    }

    public List<Position> listSummarized() {
        return repo.listSummarized();
    }

    public Position read(String id) {
        return repo.read(id);
    }

    public Position create(Position position) {
        return repo.create(position);
    }

    public Position update(String id, Position position) {
        return repo.update(id, position);
    }

    public Position delete(String id) {
        return repo.delete(id);
    }
}
