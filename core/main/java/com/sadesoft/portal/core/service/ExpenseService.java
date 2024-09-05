package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.detailed.DetailedExpense;
import com.sadesoft.portal.core.query.ExpenseQuery;
import com.sadesoft.portal.core.model.Expense;
import com.sadesoft.portal.core.repo.ExpenseRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class ExpenseService {
    private final ExpenseRepo repo;

    public ExpenseService(ExpenseRepo repo) {
        this.repo = repo;
    }

    public List<Expense> listBy(ExpenseQuery query) {
        return repo.listBy(query);
    }

    public List<DetailedExpense> listDetailedBy(ExpenseQuery query) {
        return repo.listDetailedBy(query);
    }

    public Expense read(String id) {
        return repo.read(id);
    }

    public DetailedExpense readDetailed(String id) {
        return repo.readDetailed(id);
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
        checkFieldFormats(expense);
    }

    private void checkMissingFields(Expense expense) {
        checkMissingField(expense.employeeId, "employeeId");
        checkMissingField(expense.formDate, "formDate");
        checkMissingField(expense.description, "description");
        checkMissingField(expense.status, "status");
    }

    private void checkFieldFormats(Expense expense) {
        checkFieldFormat(expense.employeeId, "employeeId", "^[0-9]+$");
        checkFieldFormat(expense.description, "description", "^[a-zA-Z ]+$");
        checkFieldFormat(expense.status, "status", "^[a-zA-Z]+$");
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
