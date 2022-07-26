package com.sadesoft.portal.core.sample;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.sadesoft.portal.core.model.*;

public class ReceiptSamples {
    public static Receipt validReceipt = new Receipt(
            null,
            LocalDate.of(2022, 1, 1),
            ReceiptCategory.Other,
            new BigDecimal("10.20"),
            (short) 18,
            "TaxiCoast");
}
