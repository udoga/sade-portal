package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;

import java.util.Objects;

public class ReceiptCategory implements Record {
    public String id;
    public String name;

    public ReceiptCategory(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public ReceiptCategory(){}

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ReceiptCategory)) return false;
        ReceiptCategory w = (ReceiptCategory) o;
        return Objects.equals(id, w.id) && Objects.equals(name, w.name);
    }

    @Override
    public String toString() {
        return "ReceiptCategory[" +
                "id=" + id +
                " name=" + name +
                ']';
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public ReceiptCategory copy() {
        try {
            return (ReceiptCategory) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public ReceiptCategory withId(String id) {
        ReceiptCategory receiptCategory = copy();
        receiptCategory.id = id;
        return receiptCategory;
    }

    public ReceiptCategory withName(String name) {
        ReceiptCategory receiptCategory = copy();
        receiptCategory.name = name;
        return receiptCategory;
    }
}
