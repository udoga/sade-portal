package com.sadesoft.portal.core.model;

import java.util.Objects;
import com.sadesoft.portal.base.repo.Record;
import com.sadesoft.portal.core.query.ReceiptQuery;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Receipt implements Record {
    public String id;
    public String expenseId;
    public LocalDate receiptDate;
    public String categoryId;
    public BigDecimal amount;
    public String currency;
    public Short vatRate;
    public String description;

    public Receipt() {
    }

    public Receipt(String id,
                   String expenseId,
                   LocalDate receiptDate,
                   String categoryId,
                   BigDecimal amount,
                   String currency,
                   Short vatRate,
                   String description) {
        this.id = id;
        this.expenseId = expenseId;
        this.receiptDate = receiptDate;
        this.categoryId = categoryId;
        this.amount = amount;
        this.currency = currency;
        this.vatRate = vatRate;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Receipt)) return false;
        Receipt u = (Receipt) o;
        return Objects.equals(id, u.id) &&
                Objects.equals(expenseId, u.expenseId) &&
                Objects.equals(receiptDate, u.receiptDate) &&
                Objects.equals(categoryId, u.categoryId) &&
                Objects.equals(amount, u.amount) &&
                Objects.equals(currency, u.currency) &&
                Objects.equals(vatRate, u.vatRate) &&
                Objects.equals(description, u.description);
    }

    @Override
    public String toString() {
        return "Receipt[" +
                "id=" + id +
                " expenseId=" + expenseId +
                " receiptDate=" + receiptDate +
                " categoryId=" + categoryId +
                " amount=" + amount +
                " currency=" + currency +
                " vatRate=" + vatRate +
                " description=" + description +
                ']';
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Receipt copy() {
        try {
            return (Receipt) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Receipt withId(String id) {
        Receipt receipt = copy();
        receipt.id = id;
        return receipt;
    }

    public Receipt withExpenseId(String expenseId) {
        Receipt receipt = copy();
        receipt.expenseId = expenseId;
        return receipt;
    }

    public Receipt withReceiptDate(LocalDate receiptDate) {
        Receipt receipt = copy();
        receipt.receiptDate = receiptDate;
        return receipt;
    }

    public Receipt withReceiptCategoryId(String categoryId) {
        Receipt receipt = copy();
        receipt.categoryId = categoryId;
        return receipt;
    }

    public Receipt withAmount(BigDecimal amount) {
        Receipt receipt = copy();
        receipt.amount = amount;
        return receipt;
    }

    public Receipt withCurrency(String currency) {
        Receipt receipt = copy();
        receipt.currency = currency;
        return receipt;
    }

    public Receipt withVatRate(Short vatRate) {
        Receipt receipt = copy();
        receipt.vatRate = vatRate;
        return receipt;
    }

    public Receipt withDescription(String description) {
        Receipt receipt = copy();
        receipt.description = description;
        return receipt;
    }

    public boolean matches(ReceiptQuery q) {
        if (q.expenseId != null && !expenseId.equals(q.expenseId)) return false;
        return true;
    }
}
