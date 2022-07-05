package com.sadesoft.portal.core.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemEnumTest {
    @Test
    public void initializesFromString() {
        assertNull(ItemEnum.from(""));
        assertEquals(ItemEnum.ValueOne, ItemEnum.from("ValueOne"));
        assertThrows(IllegalArgumentException.class, () -> ItemEnum.from("Missing"));
    }
}
