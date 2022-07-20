package com.sadesoft.portal.core.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.sadesoft.portal.base.repo.Record;

public class Item implements Record {
    public Integer id = null;
    public Byte byteField = null;
    public Short shortField = null;
    public Integer intField = null;
    public Long longField = null;
    public Double doubleField = null;
    public Float floatField = null;
    public BigDecimal decimalField = null;
    public Boolean booleanField = null;
    public Character charField = null;
    public String stringField = null;
    public LocalDate dateField = null;
    public ItemEnum enumField = null;

    public Item() {
    }

    public Item(Integer id,
                byte byteField,
                short shortField,
                int intField,
                long longField,
                double doubleField,
                float floatField,
                BigDecimal decimalField,
                boolean booleanField,
                char charField,
                String stringField,
                LocalDate dateField,
                ItemEnum enumField) {
        this.id = id;
        this.byteField = byteField;
        this.shortField = shortField;
        this.intField = intField;
        this.longField = longField;
        this.doubleField = doubleField;
        this.floatField = floatField;
        this.decimalField = decimalField;
        this.booleanField = booleanField;
        this.charField = charField;
        this.stringField = stringField;
        this.dateField = dateField;
        this.enumField = enumField;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
