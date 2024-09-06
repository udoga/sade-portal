package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.model.VacationCategory;
import com.sadesoft.portal.core.repo.VacationCategoryRepo;

import java.util.List;

public class VacationCategoryService {
    private final VacationCategoryRepo repo;

    public VacationCategoryService(VacationCategoryRepo repo) {
        this.repo = repo;
    }

    public List<VacationCategory> list() {
        return repo.list();
    }

    public List<VacationCategory> listOptions() {
        return repo.listOptions();
    }

    public VacationCategory read(String id) {
        return repo.read(id);
    }

    public VacationCategory create(VacationCategory vacationCategory) {
        return repo.create(vacationCategory);
    }

    public VacationCategory delete(String id) {
        return repo.delete(id);
    }
}
