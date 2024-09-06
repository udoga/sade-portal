package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.model.ReceiptCategory;
import com.sadesoft.portal.core.repo.ReceiptCategoryRepo;

import java.util.List;
import java.util.regex.Pattern;

public class ReceiptCategoryService {
    private final ReceiptCategoryRepo repo;

    public ReceiptCategoryService(ReceiptCategoryRepo repo) {
        this.repo = repo;
    }

    public List<ReceiptCategory> list() {
        return repo.list();
    }

    public List<ReceiptCategory> listOptions() {
        return repo.listOptions();
    }

    public ReceiptCategory read(String id) {
        return repo.read(id);
    }

    public ReceiptCategory create(ReceiptCategory receiptCategory) {
        check(receiptCategory);
        return repo.create(receiptCategory);
    }

    public ReceiptCategory delete(String id) {
        return repo.delete(id);
    }

    private void check(ReceiptCategory receiptCategory) {
        checkMissingField(receiptCategory.name, "name");
        checkFieldFormat(receiptCategory.name, "name", "^[a-zA-Z ]+$");
    }

    private void checkMissingField(String value, String field) {
        if (value == null) throw new MissingFieldError(field);
    }

    private void checkFieldFormat(String value, String field, String regex) {
        if (!Pattern.matches(regex, value)) throw new InvalidInputError("Format of field '" + field + "' is invalid.");
    }
}
