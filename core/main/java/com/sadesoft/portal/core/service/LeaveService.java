package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.ClientError;
import com.sadesoft.portal.base.error.EmptyFieldError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.model.ApprovalStatus;
import com.sadesoft.portal.core.model.Leave;
import com.sadesoft.portal.core.repo.LeaveRepo;

import java.util.List;

public class LeaveService {
    private final LeaveRepo repo;

    public LeaveService(LeaveRepo repo) {
        this.repo = repo;
    }

    public List<Leave> list() {
        return repo.list();
    }

    public List<Leave> listPage(int pageNo, int pageSize) {
        return repo.listPage(pageNo, pageSize);
    }

    public List<Leave> listFiltered(ApprovalStatus status, int pageNo, int pageSize) {
        return repo.listFiltered(status ,pageNo, pageSize);
    }

    public Leave read(String id) {
        return repo.read(id);
    }

    public Leave create(Leave leave) {
        check(leave);
        return repo.create(leave.withStatus(ApprovalStatus.Waiting));
    }

    public Leave update(String id, Leave leave) {
        check(leave);
        return repo.update(id, leave.withStatus(ApprovalStatus.Waiting));
    }

    public Leave delete(String id) {
        return repo.delete(id);
    }

    private void check(Leave leave) {
        checkMissingFields(leave);
        checkEmptyFields(leave);
        checkForbiddenField(leave.status, "status");
    }

    private void checkMissingFields(Leave leave) {
        checkMissingField(leave.personId, "personId");
        checkMissingField(leave.leaveType, "leaveType");
        checkMissingField(leave.startTime, "startTime");
        checkMissingField(leave.endTime, "endTime");
        checkMissingField(leave.dayCount, "dayCount");
        checkMissingField(leave.description, "description");
    }

    private void checkEmptyFields(Leave leave) {
        checkEmptyField(leave.description, "description");
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
