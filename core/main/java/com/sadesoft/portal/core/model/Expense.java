package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;

import java.time.LocalDate;
import java.util.Objects;

public class Expense implements Record {
    public String id = null;
    public String employeeId = null;
    public LocalDate formDate = null;
    public String description = null;
    public ApprovalStatus status = null;

    public Expense(String id,
                   String employeeId,
                   LocalDate formDate,
                   String description,
                   ApprovalStatus status) {
        this.id = id;
        this.employeeId = employeeId;
        this.formDate = formDate;
        this.description = description;
        this.status = status;
    }

    public Expense() {
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Expense)) return false;
        Expense e = (Expense) o;
        return Objects.equals(id, e.id) &&
                Objects.equals(employeeId, e.employeeId) &&
                Objects.equals(formDate, e.formDate) &&
                Objects.equals(description, e.description) &&
                Objects.equals(status, e.status);
    }

    @Override
    public String toString() {
        return "Expense[" +
                "id=" + id +
                " employeeId=" + employeeId +
                " formDate=" + formDate +
                " description=" + description +
                " status=" + status +
                "]";
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Expense copy() {
        try {
            return (Expense) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Expense withId(String id) {
        Expense expense = copy();
        expense.id = id;
        return expense;
    }

    public Expense withEmployeeId(String employeeId) {
        Expense expense = copy();
        expense.employeeId = employeeId;
        return expense;
    }

    public Expense withFormDate(LocalDate formDate) {
        Expense expense = copy();
        expense.formDate = formDate;
        return expense;
    }

    public Expense withDescription(String description) {
        Expense expense = copy();
        expense.description = description;
        return expense;
    }

    public Expense withStatus(ApprovalStatus status) {
        Expense expense = copy();
        expense.status = status;
        return expense;
    }
}
