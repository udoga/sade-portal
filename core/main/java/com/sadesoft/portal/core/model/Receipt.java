package com.sadesoft.portal.core.model;

import java.util.Objects;
import com.sadesoft.portal.base.repo.Record;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Receipt implements Record {
    public String id = null;
    public String expenseId = null;
    public LocalDate receiptDate = null;
    public String categoryId = null;
    public BigDecimal amount = null;
    public Short vatRate = null;
    public String description = null;

    public Receipt() {
    }

    public Receipt(String id,
                   String expenseId,
                   LocalDate receiptDate,
                   String categoryId,
                   BigDecimal amount,
                   Short vatRate,
                   String description) {
        this.id = id;
        this.expenseId = expenseId;
        this.receiptDate = receiptDate;
        this.categoryId = categoryId;
        this.amount = amount;
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

}
