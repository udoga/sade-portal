package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.EmptyFieldError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.base.repo.ListRepo;
import com.sadesoft.portal.core.model.Expense;
import com.sadesoft.portal.core.sample.ExpenseSamples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseServiceTest {
    private ListRepo<Expense> repo;
    private ExpenseService service;
    private Expense validExpense;

    private void assertMissingField(Expense expense) {
        assertThrows(MissingFieldError.class, () -> service.create(expense));
    }

    private void assertEmptyField(Expense expense, String field) {
        EmptyFieldError e = assertThrows(EmptyFieldError.class, () -> service.create(expense));
        assertEquals("Field '" + field + "' should not be empty.", e.getMessage());
    }

    @BeforeEach
    public void setup() {
        repo = new ListRepo<>("Expense");
        service = new ExpenseService(repo);
        validExpense = ExpenseSamples.validExpense.copy();
    }

    @Test
    public void createsExpense() {
        Expense expense = service.create(validExpense);
        assertEquals(validExpense.withId("0"), expense);
        assertEquals(1, repo.list().size());
        assertEquals(expense, repo.read("0"));
    }

    @Test
    public void whenHasMissingFieldOnCreate_throwsError() {
        assertMissingField(validExpense.withPersonId(null));
        assertMissingField(validExpense.withFormDate(null));
        assertMissingField(validExpense.withAmount(null));
        assertMissingField(validExpense.withCurrency(null));
        assertMissingField(validExpense.withPaid(null));
        assertMissingField(validExpense.withDescription(null));
        assertMissingField(validExpense.withStatus(null));
    }

    @Test
    public void whenHasEmptyFieldOnCreate_throwsError() {
        assertEmptyField(validExpense.withDescription(""), "description");
    }

    @Nested
    public class GivenServiceWithOneExpense {
        @BeforeEach
        public void setup() {
            service.create(validExpense);
        }

        @Test
        public void listsExpenses() {
            assertEquals(1, service.list().size());
            assertEquals(validExpense.withId("0"), service.list().get(0));
            assertEquals(validExpense.withId("0"), service.read("0"));
        }

        @Test
        public void updatesExpenseById() {
            Expense expense = service.update("0", validExpense.withDescription("updated"));
            assertEquals(validExpense.withId("0").withDescription("updated"), expense);
            assertEquals(expense, repo.read("0"));
        }

        @Test
        public void deletesExpenseById() {
            Expense expense = service.delete("0");
            assertEquals(validExpense.withId("0"), expense);
            assertEquals(0, service.list().size());
        }

        @Test
        public void whenHasMissingFieldOnUpdate_throwsError(){
            assertThrows(MissingFieldError.class, () -> service.update("0", validExpense.withPersonId(null)));
            assertThrows(MissingFieldError.class, () -> service.update("0", validExpense.withFormDate(null)));
        }
    }
}
