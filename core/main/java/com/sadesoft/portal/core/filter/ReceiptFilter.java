package com.sadesoft.portal.core.filter;

public class ReceiptFilter implements Cloneable {
    public String expenseId;

    public ReceiptFilter() {
    }

    public ReceiptFilter copy() {
        try {
            return (ReceiptFilter) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public ReceiptFilter withExpenseId(String expenseId) {
        ReceiptFilter filter = copy();
        filter.expenseId = expenseId;
        return filter;
    }
}
