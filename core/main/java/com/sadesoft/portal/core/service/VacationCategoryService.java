package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.model.VacationCategory;
import com.sadesoft.portal.core.repo.VacationCategoryRepo;

import java.util.List;

import java.util.regex.Pattern;

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
        check(vacationCategory);
        return repo.create(vacationCategory);
    }

    public VacationCategory delete(String id) {
        return repo.delete(id);
    }

    private void check(VacationCategory vacationCategory) {
        checkMissingField(vacationCategory.name, "name");
        checkFieldFormat(vacationCategory.name, "name", "^[a-zA-Z]+( [a-zA-Z]+)*$");
    }

    private void checkMissingField(String value, String field) {
        if (value == null) throw new MissingFieldError(field);
    }

    private void checkFieldFormat(String value, String field, String regex) {
        if (!Pattern.matches(regex, value)) throw new InvalidInputError("Format of field '" + field + "' is invalid.");
    }
}
