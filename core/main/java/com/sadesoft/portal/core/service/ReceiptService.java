package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.query.ReceiptQuery;
import com.sadesoft.portal.core.model.*;
import com.sadesoft.portal.core.repo.ReceiptRepo;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

public class ReceiptService {
    private final ReceiptRepo repo;

    public ReceiptService(ReceiptRepo repo) {
        this.repo = repo;
    }

    public List<Receipt> listBy(ReceiptQuery query) {
        return repo.listBy(query);
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
        checkMissingFields(receipt);
        checkFieldFormats(receipt);
    }

    private void checkMissingFields(Receipt receipt) {
        checkMissingField(receipt.expenseId, "expenseId");
        checkMissingField(receipt.receiptDate, "receiptDate");
        checkMissingField(receipt.categoryId, "categoryId");
        checkMissingField(receipt.amount, "amount");
        checkMissingField(receipt.currency, "currency");
        checkMissingField(receipt.vatRate, "vatRate");
        checkMissingField(receipt.description, "description");
    }

    private void checkFieldFormats(Receipt receipt) {
        checkFieldFormat(receipt.expenseId, "expenseId", "^[0-9]+$");
        checkFieldFormat(receipt.categoryId, "categoryId", "^[0-9]+$");
        checkFieldFormat(receipt.currency, "currency", "^[a-zA-Z ]+$");
        checkFieldFormat(receipt.description, "description", "^[a-zA-Z ]+$");
        checkVatRate(receipt.vatRate);
        checkAmount(receipt.amount);
    }

    private void checkFieldFormat(String value, String field, String regex) {
        if (!Pattern.matches(regex, value)) throw new InvalidInputError("Format of field '" + field + "' is invalid.");
    }

    private void checkMissingField(Object value, String field) {
        if (value == null) throw new MissingFieldError(field);
    }

    private void checkVatRate(short vatRate) {
        if (vatRate < 0 || vatRate > 100) throw new InvalidInputError("Vat rate should be between 0-100.");
    }

    private void checkAmount(BigDecimal amount) {
        int value = amount.intValue();
        if (value < 0 || value > 1000000) throw new InvalidInputError("Amount should be between 0-1000000.");
    }
}
