package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;
import com.sadesoft.portal.core.filter.ExpenseFilter;

import java.time.LocalDate;
import java.util.Objects;

public class Expense implements Record {
    public String id;
    public String employeeId;
    public LocalDate formDate;
    public String description;
    public String status;

    public Expense() {
    }

    public Expense(String id,
                   String employeeId,
                   LocalDate formDate,
                   String description,
                   String status) {
        this.id = id;
        this.employeeId = employeeId;
        this.formDate = formDate;
        this.description = description;
        this.status = status;
    }

    public Expense(Expense e) {
        this.id = e.id;
        this.employeeId = e.employeeId;
        this.formDate = e.formDate;
        this.description = e.description;
        this.status = e.status;
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

    public Expense withStatus(String status) {
        Expense expense = copy();
        expense.status = status;
        return expense;
    }

    public boolean passes(ExpenseFilter f) {
        if (f.employeeId != null && !employeeId.equals(f.employeeId)) return false;
        return true;
    }
}
