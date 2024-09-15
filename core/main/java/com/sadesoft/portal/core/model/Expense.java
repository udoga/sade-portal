package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;
import com.sadesoft.portal.core.query.ExpenseQuery;

import java.time.LocalDate;
import java.util.Objects;

public class Expense implements Record {
    public String id;
    public String employeeId;
    public LocalDate formDate;
    public String description;
    public String approvalStatus;

    public Expense() {
    }

    public Expense(String id,
                   String employeeId,
                   LocalDate formDate,
                   String description,
                   String approvalStatus) {
        this.id = id;
        this.employeeId = employeeId;
        this.formDate = formDate;
        this.description = description;
        this.approvalStatus = approvalStatus;
    }

    public Expense(Expense e) {
        this.id = e.id;
        this.employeeId = e.employeeId;
        this.formDate = e.formDate;
        this.description = e.description;
        this.approvalStatus = e.approvalStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Expense)) return false;
        Expense e = (Expense) o;
        return Objects.equals(id, e.id) &&
                Objects.equals(employeeId, e.employeeId) &&
                Objects.equals(formDate, e.formDate) &&
                Objects.equals(description, e.description) &&
                Objects.equals(approvalStatus, e.approvalStatus);
    }

    @Override
    public String toString() {
        return "Expense[" +
                "id=" + id +
                " employeeId=" + employeeId +
                " formDate=" + formDate +
                " description=" + description +
                " approvalStatus=" + approvalStatus +
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

    public Expense withApprovalStatus(String approvalStatus) {
        Expense expense = copy();
        expense.approvalStatus = approvalStatus;
        return expense;
    }

    public boolean matches(ExpenseQuery q) {
        if (q.employeeId != null && !q.employeeId.equals(employeeId)) return false;
        return true;
    }
}
