package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.model.Leave;
import java.util.List;

public class LeaveService {
    private final Repo<Leave> repo;

    public LeaveService(Repo<Leave> repo) {
        this.repo = repo;
    }

    public List<Leave> list() {
        return repo.list();
    }

    public List<Leave> listPage(int pageNo, int pageSize) {
        return repo.listPage(pageNo, pageSize);
    }

    public Leave read(String id) {
        return repo.read(id);
    }

    public Leave create(Leave leave) {
        return repo.create(leave);
    }

    public Leave update(String id, Leave leave) {
        return repo.update(id, leave);
    }

    public Leave delete(String id) {
        return repo.delete(id);
    }
}
