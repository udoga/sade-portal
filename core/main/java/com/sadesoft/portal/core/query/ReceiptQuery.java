package com.sadesoft.portal.core.query;

public class ReceiptQuery implements Cloneable {
    public String expenseId;

    public ReceiptQuery() {
    }

    public ReceiptQuery copy() {
        try {
            return (ReceiptQuery) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public ReceiptQuery withExpenseId(String expenseId) {
        ReceiptQuery copy = copy();
        copy.expenseId = expenseId;
        return copy;
    }
}
