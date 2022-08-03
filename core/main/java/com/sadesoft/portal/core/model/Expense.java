package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Expense implements Record, Cloneable {
    public Integer id;
    public Integer personId;
    public LocalDate formDate;
    public BigDecimal amount;
    public Currency currency;
    public Boolean paid;
    public String description;
    public ApprovalStatus status;

    public Expense(Integer id,
                   Integer personId,
                   LocalDate formDate,
                   BigDecimal amount,
                   Currency currency,
                   Boolean paid,
                   String description,
                   ApprovalStatus status) {
        this.id = id;
        this.personId = personId;
        this.formDate = formDate;
        this.amount = amount;
        this.currency = currency;
        this.paid = paid;
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
                Objects.equals(personId, e.personId) &&
                Objects.equals(formDate, e.formDate) &&
                Objects.equals(amount, e.amount) &&
                Objects.equals(currency, e.currency) &&
                Objects.equals(paid, e.paid) &&
                Objects.equals(description, e.description) &&
                Objects.equals(status, e.status);
    }

    @Override
    public String toString() {
        return "Expense[" +
                "id=" + id +
                " personId=" + personId +
                " formDate=" + formDate +
                " amount=" + amount +
                " currency=" + currency +
                " paid=" + paid +
                " description=" + description +
                " status=" + status +
                "]";
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Expense copy() {
        try {
            return (Expense) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Expense withId(Integer id) {
        Expense expense = copy();
        expense.id = id;
        return expense;
    }

    public Expense withPersonId(Integer personId) {
        Expense expense = copy();
        expense.personId = personId;
        return expense;
    }

    public Expense withFormDate(LocalDate formDate) {
        Expense expense = copy();
        expense.formDate = formDate;
        return expense;
    }

    public Expense withAmount(BigDecimal amount) {
        Expense expense = copy();
        expense.amount = amount;
        return expense;
    }

    public Expense withCurrency(Currency currency) {
        Expense expense = copy();
        expense.currency = currency;
        return expense;
    }

    public Expense withPaid(Boolean paid) {
        Expense expense = copy();
        expense.paid = paid;
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
