package com.sadesoft.portal.core.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.sadesoft.portal.core.sample.ReceiptSamples;

public class ReceiptTest {
    private Receipt validReceipt;

    @BeforeEach
    public void setup() {
        validReceipt = ReceiptSamples.validReceipt;
    }

    @Test
    public void initializesWithoutFields() {
        Receipt receipt = new Receipt();
        assertNull(receipt.id);
        assertNull(receipt.receiptDate);
        assertNull(receipt.category);
        assertNull(receipt.amount);
        assertNull(receipt.vatRate);
        assertNull(receipt.description);
    }

    @Test
    public void printsFields() {
        assertEquals("Receipt[id=null" +
                " receiptDate=2022-01-01" +
                " category=Other" +
                " amount=10.20" +
                " vatRate=18" +
                " description=TaxiCoast" +
                "]", validReceipt.toString());
    }

    @Test
    public void clonesWithFields() {
        assertNull(validReceipt.withId(null).id);
        assertNull(validReceipt.withReceiptDate(null).receiptDate);
        assertNull(validReceipt.withReceiptCategory(null).category);
        assertNull(validReceipt.withAmount(null).amount);
        assertNull(validReceipt.withVatRate(null).vatRate);
        assertNull(validReceipt.withDescription(null).description);
    }

    @Test
    public void checksEquality() {
        assertEquals(validReceipt, validReceipt.copy());
        assertNotEquals(validReceipt, validReceipt.withId(0));
        assertNotEquals(validReceipt, validReceipt.withReceiptDate(null));
        assertNotEquals(validReceipt, validReceipt.withReceiptCategory(null));
        assertNotEquals(validReceipt, validReceipt.withAmount(null));
        assertNotEquals(validReceipt, validReceipt.withVatRate(null));
        assertNotEquals(validReceipt, validReceipt.withDescription(null));
    }
}
