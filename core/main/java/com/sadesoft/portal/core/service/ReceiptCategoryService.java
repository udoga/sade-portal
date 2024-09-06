package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.model.ReceiptCategory;
import com.sadesoft.portal.core.repo.ReceiptCategoryRepo;

import java.util.List;

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
        return repo.create(receiptCategory);
    }

    public ReceiptCategory delete(String id) {
        return repo.delete(id);
    }
}
