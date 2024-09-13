package com.sadesoft.portal.core.service;

import static com.sadesoft.portal.base.util.TestUtil.*;
import com.sadesoft.portal.core.mock.MockExpenseRepo;
import com.sadesoft.portal.core.query.ExpenseQuery;
import com.sadesoft.portal.core.mock.MockEmployeeRepo;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.Expense;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
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
        assertMissingField(() -> service.create(validExpense.withEmployeeId(null)));
        assertMissingField(() -> service.create(validExpense.withFormDate(null)));
        assertMissingField(() -> service.create(validExpense.withDescription(null)));
        assertMissingField(() -> service.create(validExpense.withApprovalStatus(null)));
    }

    @Test
    public void whenHasInvalidInputOnCreate_throwsError() throws Exception {
        assertIdField("employeeId");
        assertInvalidInput(() -> service.create(validExpense.withApprovalStatus("1")));
        assertInvalidInput(() -> service.create(validExpense.withApprovalStatus("")));
    }

    private void assertIdField(String fieldName) throws Exception {
        assertInvalidInput(() -> service.create(withField(validExpense, fieldName, "A")));
        assertInvalidInput(() -> service.create(withField(validExpense, fieldName, "")));
        assertDoesNotThrow(() -> service.create(withField(validExpense, fieldName, "11")));
    }

    private Expense withField(Expense expense, String fieldName, String value) throws Exception {
        Field field = expense.getClass().getDeclaredField(fieldName);
        field.set(expense, value);
        return expense;
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
            assertMissingField(() -> service.update("0", validExpense.withEmployeeId(null)));
            assertMissingField(() -> service.update("0", validExpense.withFormDate(null)));
            assertMissingField(() -> service.update("0", validExpense.withDescription(null)));
            assertMissingField(() -> service.update("0", validExpense.withApprovalStatus(null)));
        }
    }
}
