package com.sadesoft.portal.core.model;

import com.sadesoft.portal.core.sample.ExpenseSamples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ExpenseTest {
    private Expense validExpense;

    @BeforeEach
    public void setup() {
        validExpense = ExpenseSamples.validExpense.copy();
    }

    @Test
    public void initializesWithoutFields() {
        Expense expense = new Expense();
        assertNull(expense.id);
        assertNull(expense.personId);
        assertNull(expense.formDate);
        assertNull(expense.amount);
        assertNull(expense.currency);
        assertNull(expense.paid);
        assertNull(expense.description);
        assertNull(expense.status);
    }

    @Test
    public void printsFields() {
        assertEquals("Expense[id=null" +
                " personId=1" +
                " formDate=2022-01-01" +
                " amount=1000.10" +
                " currency=EUR" +
                " paid=true" +
                " description=Example expense" +
                " status=Approved" +
                "]", validExpense.toString());
    }

    @Test
    public void clonesWithFields() {
        assertNull(validExpense.withId(null).id);
        assertNull(validExpense.withPersonId(null).personId);
        assertNull(validExpense.withFormDate(null).formDate);
        assertNull(validExpense.withAmount(null).amount);
        assertNull(validExpense.withCurrency(null).currency);
        assertNull(validExpense.withPaid(null).paid);
        assertNull(validExpense.withDescription(null).description);
        assertNull(validExpense.withStatus(null).status);
    }

    @Test
    public void checksEquality() {
        assertEquals(validExpense, validExpense.copy());
        assertNotEquals(validExpense, validExpense.withId("0"));
        assertNotEquals(validExpense, validExpense.withPersonId(null));
        assertNotEquals(validExpense, validExpense.withFormDate(null));
        assertNotEquals(validExpense, validExpense.withAmount(null));
        assertNotEquals(validExpense, validExpense.withCurrency(null));
        assertNotEquals(validExpense, validExpense.withPaid(null));
        assertNotEquals(validExpense, validExpense.withDescription(null));
        assertNotEquals(validExpense, validExpense.withStatus(null));
    }
}
