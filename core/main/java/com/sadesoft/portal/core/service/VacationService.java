package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.ClientError;
import com.sadesoft.portal.base.error.EmptyFieldError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.detailed.DetailedVacation;
import com.sadesoft.portal.core.query.VacationQuery;
import com.sadesoft.portal.core.model.Vacation;
import com.sadesoft.portal.core.repo.VacationRepo;

import java.util.List;

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
        return repo.create(vacation.withStatus("Waiting"));
    }

    public Vacation update(String id, Vacation vacation) {
        check(vacation);
        return repo.update(id, vacation.withStatus("Waiting"));
    }

    public Vacation delete(String id) {
        return repo.delete(id);
    }

    private void check(Vacation vacation) {
        checkMissingFields(vacation);
        checkEmptyFields(vacation);
        checkForbiddenField(vacation.status, "status");
    }

    private void checkMissingFields(Vacation vacation) {
        checkMissingField(vacation.employeeId, "employeeId");
        checkMissingField(vacation.categoryId, "categoryId");
        checkMissingField(vacation.startTime, "startTime");
        checkMissingField(vacation.endTime, "endTime");
        checkMissingField(vacation.dayCount, "dayCount");
        checkMissingField(vacation.description, "description");
    }

    private void checkEmptyFields(Vacation vacation) {
        checkEmptyField(vacation.description, "description");
    }

    private void checkMissingField(Object value, String field) {
        if (value == null) throw new MissingFieldError(field);
    }

    private void checkForbiddenField(Object value, String field) {
        if (value != null) throw new ClientError("Field '" + field + "' should not exist.");
    }

    private void checkEmptyField(String value, String field) {
        if (value.isEmpty()) throw new EmptyFieldError(field);
    }

}
