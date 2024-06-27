package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.model.*;
import com.sadesoft.portal.core.repo.ReceiptRepo;

import java.math.BigDecimal;
import java.util.List;

public class ReceiptService {
    private final ReceiptRepo repo;

    public ReceiptService(ReceiptRepo repo) {
        this.repo = repo;
    }

    public List<Receipt> list() {
        return repo.list();
    }

    public List<Receipt> paginate(int pageNo, int pageSize) {
        return repo.paginate(pageNo, pageSize);
    }

    public Receipt read(String id) {
        return repo.read(id);
    }

    public Receipt create(Receipt receipt) {
        check(receipt);
        return repo.create(receipt);
    }

    public Receipt update(String id, Receipt receipt) {
        check(receipt);
        return repo.update(id, receipt);
    }

    public Receipt delete(String id) {
        return repo.delete(id);
    }

    private void check(Receipt receipt) {
        checkMissingField(receipt.expenseId, "expenseId");
        checkMissingField(receipt.receiptDate, "receiptDate");
        checkMissingField(receipt.categoryId, "categoryId");
        checkMissingField(receipt.amount, "amount");
        checkMissingField(receipt.vatRate, "vatRate");
        checkMissingField(receipt.description, "description");
        checkVatRate(receipt.vatRate);
        checkAmount(receipt.amount);
    }

    private void checkVatRate(short vatRate) {
        if (vatRate < 0 || vatRate > 100) throw new InvalidInputError("Vat rate should be between 0-100.");
    }

    private void checkAmount(BigDecimal amount) {
        int value = amount.intValue();
        if (value < 0 || value > 1000000) throw new InvalidInputError("Amount should be between 0-1000000.");
    }

    private void checkMissingField(Object value, String field) {
        if (value == null) throw new MissingFieldError(field);
    }
}
