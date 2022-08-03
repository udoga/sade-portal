package com.sadesoft.portal.core.sample;

import com.sadesoft.portal.core.model.ApprovalStatus;
import com.sadesoft.portal.core.model.Currency;
import com.sadesoft.portal.core.model.Expense;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseSamples {
    public static Expense validExpense = new Expense(
            null,
            1,
            LocalDate.of(2022, 1, 1),
            new BigDecimal("1000.10"),
            Currency.EUR,
            true,
            "Example expense",
            ApprovalStatus.Approved);
}
