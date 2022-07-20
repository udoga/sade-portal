package com.sadesoft.portal.core.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    @Test
    public void initializes() {
        Item item = new Item(1, (byte) 2, (short) 3, 4, 5, 6.5, (float) 7.5, BigDecimal.ONE, true, 'A', "ABC",
                LocalDate.of(2000, 1, 1), ItemEnum.ValueOne);
        assertEquals(1, item.id);
        assertEquals(2, item.byteField.byteValue());
        assertEquals(3, item.shortField.shortValue());
        assertEquals(4, item.intField);
        assertEquals(5, item.longField);
        assertEquals(6.5, item.doubleField);
        assertEquals(7.5, item.floatField.floatValue());
        assertEquals(BigDecimal.ONE, item.decimalField);
        assertTrue(item.booleanField);
        assertEquals('A', item.charField);
        assertEquals("ABC", item.stringField);
        assertEquals(LocalDate.of(2000, 1, 1), item.dateField);
        assertEquals(ItemEnum.ValueOne, item.enumField);
    }
}
