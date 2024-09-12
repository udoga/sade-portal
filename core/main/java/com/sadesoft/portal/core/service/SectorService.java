package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.model.Sector;
import com.sadesoft.portal.core.repo.SectorRepo;

import java.util.List;
import java.util.regex.Pattern;

public class SectorService {
    private final SectorRepo repo;

    public SectorService(SectorRepo repo) {
        this.repo = repo;
    }

    public List<Sector> list() {
        return repo.list();
    }

    public List<Sector> listOptions() {
        return repo.listOptions();
    }

    public Sector read(String id) {
        return repo.read(id);
    }

    public Sector create(Sector sector) {
        check(sector);
        return repo.create(sector);
    }

    public Sector delete(String id) {
        return repo.delete(id);
    }

    private void check(Sector sector) {
        checkMissingField(sector.name, "name");
        checkFieldFormat(sector.name, "name", "^[a-zA-Z]+( [a-zA-Z]+)*$");
    }

    private void checkMissingField(String value, String field) {
        if (value == null) throw new MissingFieldError(field);
    }

    private void checkFieldFormat(String value, String field, String regex) {
        if (!Pattern.matches(regex, value)) throw new InvalidInputError("Format of field '" + field + "' is invalid.");
    }
}
