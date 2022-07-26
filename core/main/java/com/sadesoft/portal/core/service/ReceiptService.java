package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.model.*;
import java.util.List;

public class ReceiptService {
    private final Repo<Receipt> repo;

    public ReceiptService(Repo<Receipt> repo) {
        this.repo = repo;
    }

    public List<Receipt> list() {
        return repo.list();
    }

    public Receipt read(int id) {
        return repo.read(id);
    }

    public Receipt create(Receipt receipt) {
        validateFields(receipt);
        return repo.create(receipt);
    }

    public Receipt update(int id, Receipt receipt) {
        validateFields(receipt);
        return repo.update(id, receipt);
    }

    public Receipt delete(int id) {
        return repo.delete(id);
    }

    private void validateFields(Receipt receipt) {
        validateExists(receipt.receiptDate, "receiptDate");
        validateExists(receipt.category, "receiptCategory");
        validateExists(receipt.amount, "amount");
        validateExists(receipt.vatRate, "vatRate");
        validateExists(receipt.description, "description");
    }

    private void validateExists(Object fieldValue, String fieldName) {
        if (fieldValue == null) throw new MissingFieldError("Receipt", fieldName);
    }
}
