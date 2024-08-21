package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.EmptyFieldError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.mock.MockExpenseRepo;
import com.sadesoft.portal.core.filter.ExpenseFilter;
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

    private void assertMissingField(Expense expense) {
        assertThrows(MissingFieldError.class, () -> service.create(expense));
    }

    private void assertEmptyField(Expense expense, String field) {
        EmptyFieldError e = assertThrows(EmptyFieldError.class, () -> service.create(expense));
        assertEquals("Field '" + field + "' should not be empty.", e.getMessage());
    }

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
        assertMissingField(validExpense.withEmployeeId(null));
        assertMissingField(validExpense.withFormDate(null));
        assertMissingField(validExpense.withDescription(null));
        assertMissingField(validExpense.withStatus(null));
    }

    @Test
    public void whenDescriptionIsEmpty_throwsError() {
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
            assertEquals(1, service.listBy(new ExpenseFilter()).size());
            assertEquals(validExpense.withId("0"), service.listBy(new ExpenseFilter()).get(0));
            assertEquals(validExpense.withId("0"), service.read("0"));
        }

        @Test
        public void listsByEmployeeId() {
            assertEquals(1, service.listBy(new ExpenseFilter().withEmployeeId("0")).size());
            assertEquals(0, service.listBy(new ExpenseFilter().withEmployeeId("1")).size());
        }

        @Test
        public void listsDetailedExpenses() {
            employeeRepo.create(new Employee());
            assertEquals(expenseRepo.listDetailedBy(new ExpenseFilter()), service.listDetailedBy(new ExpenseFilter()));
        }

        @Test
        public void listsDetailedByEmployeeId() {
            employeeRepo.create(new Employee());
            assertEquals(1, service.listDetailedBy(new ExpenseFilter().withEmployeeId("0")).size());
            assertEquals(0, service.listDetailedBy(new ExpenseFilter().withEmployeeId("1")).size());
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
            assertEquals(0, service.listBy(new ExpenseFilter()).size());
        }

        @Test
        public void whenHasMissingFieldOnUpdate_throwsError(){
            assertThrows(MissingFieldError.class, () -> service.update("0", validExpense.withEmployeeId(null)));
            assertThrows(MissingFieldError.class, () -> service.update("0", validExpense.withFormDate(null)));
        }
    }
}
