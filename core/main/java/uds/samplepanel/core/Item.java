package uds.samplepanel.core;

import java.math.BigDecimal;
import java.time.LocalDate;
import uds.samplepanel.base.Record;

public class Item extends Record {
    public byte byteField;
    public short shortField;
    public int intField;
    public long longField;
    public double doubleField;
    public float floatField;
    public BigDecimal decimalField;
    public boolean booleanField;
    public char charField;
    public String stringField;
    public LocalDate dateField;
    public ItemEnum enumField;

    public Item() {
        this(null, (byte) 0, (short) 0, 0, 0, 0, 0, BigDecimal.ZERO, false, ' ', "", LocalDate.EPOCH, null);
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
}
