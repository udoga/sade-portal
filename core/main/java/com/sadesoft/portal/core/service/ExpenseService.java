package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.EmptyFieldError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.model.Expense;
import com.sadesoft.portal.core.repo.ExpenseRepo;

import java.util.List;

public class ExpenseService {
    private final ExpenseRepo repo;

    public ExpenseService(ExpenseRepo repo) {
        this.repo = repo;
    }

    public List<Expense> list() {
        return repo.list();
    }

    public List<Expense> listPage(int pageNo, int pageSize) {
        return repo.listPage(pageNo, pageSize);
    }

    public Expense read(String id) {
        return repo.read(id);
    }

    public Expense create(Expense expense) {
        check(expense);
        return repo.create(expense);
    }

    public Expense update(String id, Expense expense) {
        check(expense);
        return repo.update(id, expense);
    }

    public Expense delete(String id) {
        return repo.delete(id);
    }

    private void check(Expense expense) {
        checkMissingFields(expense);
        checkEmptyFields(expense);
    }

    private void checkMissingFields(Expense expense) {
        checkMissingField(expense.employeeId, "userId");
        checkMissingField(expense.formDate, "formDate");
        checkMissingField(expense.amount, "amount");
        checkMissingField(expense.currency, "currency");
        checkMissingField(expense.paid, "paid");
        checkMissingField(expense.description, "description");
        checkMissingField(expense.status, "status");
    }

    private void checkEmptyFields(Expense expense) {
        checkEmptyField(expense.description, "description");
    }

    private void checkMissingField(Object value, String field) {
        if (value == null) throw new MissingFieldError(field);
    }

    private void checkEmptyField(String value, String field) {
        if (value.isEmpty()) throw new EmptyFieldError(field);
    }
}
