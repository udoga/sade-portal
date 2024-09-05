package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.mock.MockExpenseRepo;
import com.sadesoft.portal.core.query.ExpenseQuery;
import com.sadesoft.portal.core.mock.MockEmployeeRepo;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.Expense;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseServiceTest {
    private MockExpenseRepo expenseRepo;
    private MockEmployeeRepo employeeRepo;
    private ExpenseService service;
    private Expense validExpense;

    @BeforeEach
    public void setup() {
        employeeRepo = new MockEmployeeRepo(null);
        expenseRepo = new MockExpenseRepo(employeeRepo);
        service = new ExpenseService(expenseRepo);
        validExpense = new Expense(
                null,
                "0",
                LocalDate.of(2022, 1, 1),
                "Example expense",
                "Approved");
    }

    @Test
    public void createsExpense() {
        Expense expense = service.create(validExpense);
        assertEquals(validExpense.withId("0"), expense);
        assertEquals(1, expenseRepo.list().size());
        assertEquals(expense, expenseRepo.read("0"));
    }

    @Test
    public void whenHasMissingFieldOnCreate_throwsError() {
        assertMissingFieldOnCreate(validExpense.withEmployeeId(null));
        assertMissingFieldOnCreate(validExpense.withFormDate(null));
        assertMissingFieldOnCreate(validExpense.withDescription(null));
        assertMissingFieldOnCreate(validExpense.withStatus(null));
    }

    @Test
    public void whenHasInvalidInputOnCreate_throwsError() {
        assertInvalidInputOnCreate(validExpense.withEmployeeId("A"));
        assertInvalidInputOnCreate(validExpense.withEmployeeId(""));
        assertInvalidInputOnCreate(validExpense.withDescription("1"));
        assertInvalidInputOnCreate(validExpense.withDescription(""));
        assertInvalidInputOnCreate(validExpense.withStatus("1"));
        assertInvalidInputOnCreate(validExpense.withStatus(""));
        assertDoesNotThrow(() -> service.create(validExpense.withEmployeeId("11")));
        assertDoesNotThrow(() -> service.create(validExpense.withDescription("A B")));
    }

    private void assertMissingFieldOnCreate(Expense expense) {
        assertThrows(MissingFieldError.class, () -> service.create(expense));
    }

    private void assertInvalidInputOnCreate(Expense expense) {
        assertThrows(InvalidInputError.class, () -> service.create(expense));
    }

    @Nested
    public class GivenServiceWithOneExpense {
        @BeforeEach
        public void setup() {
            service.create(validExpense);
        }

        @Test
        public void listsExpenses() {
            assertEquals(1, service.listBy(new ExpenseQuery()).size());
            assertEquals(validExpense.withId("0"), service.listBy(new ExpenseQuery()).get(0));
            assertEquals(validExpense.withId("0"), service.read("0"));
        }

        @Test
        public void listsByEmployeeId() {
            assertEquals(1, service.listBy(new ExpenseQuery().withEmployeeId("0")).size());
            assertEquals(0, service.listBy(new ExpenseQuery().withEmployeeId("1")).size());
        }

        @Test
        public void listsDetailedExpenses() {
            employeeRepo.create(new Employee());
            assertEquals(expenseRepo.listDetailedBy(new ExpenseQuery()), service.listDetailedBy(new ExpenseQuery()));
        }

        @Test
        public void listsDetailedByEmployeeId() {
            employeeRepo.create(new Employee());
            assertEquals(1, service.listDetailedBy(new ExpenseQuery().withEmployeeId("0")).size());
            assertEquals(0, service.listDetailedBy(new ExpenseQuery().withEmployeeId("1")).size());
        }

        @Test
        public void readsDetailedVacation() {
            employeeRepo.create(new Employee(null, null, "first", "last"));
            assertEquals(expenseRepo.readDetailed("0"), service.readDetailed("0"));
        }

        @Test
        public void updatesExpenseById() {
            Expense expense = service.update("0", validExpense.withDescription("updated"));
            assertEquals(validExpense.withId("0").withDescription("updated"), expense);
            assertEquals(expense, expenseRepo.read("0"));
        }

        @Test
        public void deletesExpenseById() {
            Expense expense = service.delete("0");
            assertEquals(validExpense.withId("0"), expense);
            assertEquals(0, service.listBy(new ExpenseQuery()).size());
        }

        @Test
        public void whenHasMissingFieldOnUpdate_throwsError() {
            assertMissingFieldOnUpdate(validExpense.withEmployeeId(null));
            assertMissingFieldOnUpdate(validExpense.withFormDate(null));
            assertMissingFieldOnUpdate(validExpense.withDescription(null));
            assertMissingFieldOnUpdate(validExpense.withStatus(null));
        }

        @Test
        public void whenHasInvalidInputOnUpdate_throwsError() {
            assertInvalidInputOnUpdate(validExpense.withEmployeeId("A"));
            assertInvalidInputOnUpdate(validExpense.withEmployeeId(""));
            assertInvalidInputOnUpdate(validExpense.withDescription("1"));
            assertInvalidInputOnUpdate(validExpense.withDescription(""));
            assertInvalidInputOnUpdate(validExpense.withStatus("1"));
            assertInvalidInputOnUpdate(validExpense.withStatus(""));
            assertDoesNotThrow(() -> service.update("0", validExpense.withEmployeeId("11")));
            assertDoesNotThrow(() -> service.update("0", validExpense.withDescription("A B")));
        }

        private void assertMissingFieldOnUpdate(Expense expense) {
            assertThrows(MissingFieldError.class, () -> service.update("0", expense));
        }

        private void assertInvalidInputOnUpdate(Expense expense) {
            assertThrows(InvalidInputError.class, () -> service.update("0", expense));
        }
    }
}
