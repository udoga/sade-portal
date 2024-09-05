package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.detailed.DetailedGrant;
import com.sadesoft.portal.core.query.GrantQuery;
import com.sadesoft.portal.core.model.Grant;
import com.sadesoft.portal.core.repo.GrantRepo;

import java.util.List;
import java.util.regex.Pattern;

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
        check(grant);
        return repo.create(grant);
    }

    public Grant delete(String id) {
        return repo.delete(id);
    }

    private void check(Grant grant) {
        checkMissingFields(grant);
        checkFieldFormats(grant);
    }

    private void checkMissingFields(Grant grant) {
        checkMissingField(grant.roleId, "roleId");
        checkMissingField(grant.permissionId, "permissionId");
    }

    private void checkFieldFormats(Grant grant) {
        checkFieldFormat(grant.roleId, "roleId", "^[0-9]+$");
        checkFieldFormat(grant.permissionId, "permissionId", "^[0-9]+$");
    }

    private void checkMissingField(String value, String field) {
        if (value == null) throw new MissingFieldError(field);
    }

    private void checkFieldFormat(String value, String field, String regex) {
        if (!Pattern.matches(regex, value)) throw new InvalidInputError("Format of field '" + field + "' is invalid.");
    }
}
