package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.detailed.DetailedEngagement;
import com.sadesoft.portal.core.query.EngagementQuery;
import com.sadesoft.portal.core.model.Engagement;
import com.sadesoft.portal.core.repo.EngagementRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class EngagementService {
    private final EngagementRepo repo;

    public EngagementService(EngagementRepo repo) {
        this.repo = repo;
    }

    public List<Engagement> listBy(EngagementQuery query) {
        return repo.listBy(query);
    }

    public List<DetailedEngagement> listDetailedBy(EngagementQuery query) {
        return repo.listDetailedBy(query);
    }

    public Engagement read(String id) {
        return repo.read(id);
    }

    public Engagement create(Engagement engagement) {
        check(engagement);
        return repo.create(engagement);
    }

    public Engagement update(String id, Engagement engagement) {
        check(engagement);
        return repo.update(id, engagement);
    }

    public Engagement delete(String id) {
        return repo.delete(id);
    }

    private void check(Engagement engagement) {
        checkMissingFields(engagement);
        checkFieldFormats(engagement);
    }

    private void checkMissingFields(Engagement engagement) {
        checkMissingField(engagement.projectId, "projectId");
        checkMissingField(engagement.employeeId, "employeeId");
        checkMissingField(engagement.positionId, "positionId");
        checkMissingField(engagement.startDate, "startDate");
        checkMissingField(engagement.endDate, "endDate");
        checkMissingField(engagement.description, "description");
        checkMissingField(engagement.status, "status");
    }

    private void checkFieldFormats(Engagement engagement) {
        checkFieldFormat(engagement.projectId, "projectId", "^[0-9]+$");
        checkFieldFormat(engagement.employeeId, "employeeId", "^[0-9]+$");
        checkFieldFormat(engagement.positionId, "positionId", "^[0-9]+$");
        checkFieldFormat(engagement.description, "description", "^[a-zA-Z ]+$");
        checkFieldFormat(engagement.status, "status", "^[AI]$");
    }

    private void checkMissingField(String value, String field) {
        if (value == null) throw new MissingFieldError(field);
    }

    private void checkMissingField(LocalDate date, String field) {
        if (date == null) throw new MissingFieldError(field);
    }

    private void checkFieldFormat(String value, String field, String regex) {
        if (!Pattern.matches(regex, value)) throw new InvalidInputError("Format of field '" + field + "' is invalid.");
    }
}