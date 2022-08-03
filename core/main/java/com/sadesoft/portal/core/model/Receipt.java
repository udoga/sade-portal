package com.sadesoft.portal.core.model;

import java.util.Objects;
import com.sadesoft.portal.base.repo.Record;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Receipt implements Record, Cloneable {
    public Integer id = null;
    public Integer expenseId = null;
    public LocalDate receiptDate = null;
    public ReceiptCategory category = null;
    public BigDecimal amount = null;
    public Short vatRate = null;
    public String description = null;

    public Receipt() {
    }

    public Receipt(Integer id,
                   Integer expenseId,
                   LocalDate receiptDate,
                   ReceiptCategory category,
                   BigDecimal amount,
                   Short vatRate,
                   String description) {
        this.id = id;
        this.expenseId = expenseId;
        this.receiptDate = receiptDate;
        this.category = category;
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
                Objects.equals(category, u.category) &&
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
                " category=" + category +
                " amount=" + amount +
                " vatRate=" + vatRate +
                " description=" + description +
                ']';
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Receipt copy() {
        try {
            return (Receipt) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Receipt withId(Integer id) {
        Receipt receipt = copy();
        receipt.id = id;
        return receipt;
    }

    public Receipt withExpenseId(Integer expenseId) {
        Receipt receipt = copy();
        receipt.expenseId = expenseId;
        return receipt;
    }

    public Receipt withReceiptDate(LocalDate receiptDate) {
        Receipt receipt = copy();
        receipt.receiptDate = receiptDate;
        return receipt;
    }

    public Receipt withReceiptCategory(ReceiptCategory receiptCategory) {
        Receipt receipt = copy();
        receipt.category = receiptCategory;
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
