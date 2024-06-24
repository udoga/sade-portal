package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.model.WorklogType;
import com.sadesoft.portal.core.repo.WorklogTypeRepo;

import java.util.List;

public class WorklogTypeService {
    private final WorklogTypeRepo repo;

    public WorklogTypeService(WorklogTypeRepo repo) {
        this.repo = repo;
    }

    public List<WorklogType> list() {
        return repo.list();
    }

    public WorklogType read(String id) {
        return repo.read(id);
    }

    public WorklogType create(WorklogType worklogType) {
        return repo.create(worklogType);
    }

    public WorklogType update(String id, WorklogType worklogType) {
        return repo.update(id, worklogType);
    }

    public WorklogType delete(String id) {
        return repo.delete(id);
    }
}
