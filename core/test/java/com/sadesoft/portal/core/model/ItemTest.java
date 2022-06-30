package com.sadesoft.portal.core.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    @Test
    public void initializesWithoutFields() {
        Item item = new Item();
        assertNull(item.id);
        assertEquals(0, item.byteField);
        assertEquals(0, item.shortField);
        assertEquals(0, item.intField);
        assertEquals(0, item.longField);
        assertEquals(0, item.doubleField);
        assertEquals(0, item.floatField);
        assertEquals(BigDecimal.ZERO, item.decimalField);
        assertFalse(item.booleanField);
        assertEquals(' ', item.charField);
        assertEquals("", item.stringField);
        assertEquals(LocalDate.EPOCH, item.dateField);
        assertNull(item.enumField);
    }

    @Test
    public void initializesWithFields() {
        Item item = new Item(1, (byte) 2, (short) 3, 4, 5, 6.5, (float) 7.5, BigDecimal.ONE, true, 'A', "ABC",
                LocalDate.of(2000, 1, 1), ItemEnum.ValueOne);
        assertEquals(1, item.id);
        assertEquals(2, item.byteField);
        assertEquals(3, item.shortField);
        assertEquals(4, item.intField);
        assertEquals(5, item.longField);
        assertEquals(6.5, item.doubleField);
        assertEquals(7.5, item.floatField);
        assertEquals(BigDecimal.ONE, item.decimalField);
        assertTrue(item.booleanField);
        assertEquals('A', item.charField);
        assertEquals("ABC", item.stringField);
        assertEquals(LocalDate.of(2000, 1, 1), item.dateField);
        assertEquals(ItemEnum.ValueOne, item.enumField);
    }
}
