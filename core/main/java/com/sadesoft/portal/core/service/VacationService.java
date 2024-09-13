package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.ClientError;
import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.detailed.DetailedVacation;
import com.sadesoft.portal.core.query.VacationQuery;
import com.sadesoft.portal.core.model.Vacation;
import com.sadesoft.portal.core.repo.VacationRepo;

import java.util.List;

import java.util.regex.Pattern;

public class VacationService {
    private final VacationRepo repo;

    public VacationService(VacationRepo repo) {
        this.repo = repo;
    }

    public List<Vacation> listBy(VacationQuery query) {
        return repo.listBy(query);
    }

    public List<DetailedVacation> listDetailedBy(VacationQuery query) {
        return repo.listDetailedBy(query);
    }

    public Vacation read(String id) {
        return repo.read(id);
    }

    public DetailedVacation readDetailed(String id) {
        return repo.readDetailed(id);
    }

    public Vacation create(Vacation vacation) {
        check(vacation);
        return repo.create(vacation.withApprovalStatus("Waiting"));
    }

    public Vacation update(String id, Vacation vacation) {
        check(vacation);
        return repo.update(id, vacation.withApprovalStatus("Waiting"));
    }

    public Vacation delete(String id) {
        return repo.delete(id);
    }

    private void check(Vacation vacation) {
        checkMissingFields(vacation);
        checkFieldFormats(vacation);
        checkForbiddenField(vacation.approvalStatus, "approvalStatus");
    }

    private void checkMissingFields(Vacation vacation) {
        checkMissingField(vacation.employeeId, "employeeId");
        checkMissingField(vacation.categoryId, "categoryId");
        checkMissingField(vacation.startTime, "startTime");
        checkMissingField(vacation.endTime, "endTime");
        checkMissingField(vacation.dayCount, "dayCount");
        checkMissingField(vacation.description, "description");
    }

    private void checkFieldFormats(Vacation vacation) {
        checkFieldFormat(vacation.employeeId, "employeeId", "^[0-9]+$");
        checkFieldFormat(vacation.categoryId, "categoryId", "^[0-9]+$");
    }

    private void checkMissingField(Object value, String field) {
        if (value == null) throw new MissingFieldError(field);
    }

    private void checkForbiddenField(Object value, String field) {
        if (value != null) throw new ClientError("Field '" + field + "' should not exist.");
    }

    private void checkFieldFormat(String value, String field, String regex) {
        if (!Pattern.matches(regex, value)) throw new InvalidInputError("Format of field '" + field + "' is invalid.");
    }
}
