package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.model.Position;
import com.sadesoft.portal.core.repo.PositionRepo;

import java.util.List;
import java.util.regex.Pattern;

public class PositionService {
    private final PositionRepo repo;

    public PositionService(PositionRepo repo) {
        this.repo = repo;
    }

    public List<Position> list() {
        return repo.list();
    }

    public List<Position> listOptions() {
        return repo.listOptions();
    }

    public Position read(String id) {
        return repo.read(id);
    }

    public Position create(Position position) {
        check(position);
        return repo.create(position);
    }

    public Position update(String id, Position position) {
        check(position);
        return repo.update(id, position);
    }

    public Position delete(String id) {
        return repo.delete(id);
    }

    private void check(Position position) {
        checkMissingField(position.name, "name");
        checkFieldFormat(position.name, "name", "^[a-zA-ZçÇğĞıİöÖşŞüÜ]+( [a-zA-ZçÇğĞıİöÖşŞüÜ]+)*$");
    }

    private void checkMissingField(String value, String field) {
        if (value == null) throw new MissingFieldError(field);
    }

    private void checkFieldFormat(String value, String field, String regex) {
        if (!Pattern.matches(regex, value)) throw new InvalidInputError("Format of field '" + field + "' is invalid.");
    }
}
