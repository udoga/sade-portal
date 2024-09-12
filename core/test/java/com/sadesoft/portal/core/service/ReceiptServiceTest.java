package com.sadesoft.portal.core.service;

import static com.sadesoft.portal.base.util.TestUtil.*;
import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.core.query.ReceiptQuery;
import com.sadesoft.portal.core.mock.MockReceiptRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.sadesoft.portal.core.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.lang.reflect.Field;

public class ReceiptServiceTest {
    private MockReceiptRepo repo;
    private ReceiptService service;
    private Receipt validReceipt;

    @BeforeEach
    public void setup() {
        repo = new MockReceiptRepo();
        service = new ReceiptService(repo);
        validReceipt = new Receipt(
                null,
                "1",
                LocalDate.of(2022, 1, 1),
                "1",
                new BigDecimal("10.20"),
                "TRY",
                new BigDecimal("18"),
                "TaxiCoast");
    }

    @Test
    public void createsReceipt() {
        Receipt receipt = service.create(validReceipt);
        assertEquals(validReceipt.withId("0"), receipt);
        assertEquals(1, repo.list().size());
        assertEquals(receipt, repo.read("0"));
    }

    @Test
    public void whenHasMissingField_throwsError() {
        assertMissingField(() -> service.create(validReceipt.withExpenseId(null)));
        assertMissingField(() -> service.create(validReceipt.withReceiptCategoryId(null)));
        assertMissingField(() -> service.create(validReceipt.withReceiptDate(null)));
        assertMissingField(() -> service.create(validReceipt.withAmount(null)));
        assertMissingField(() -> service.create(validReceipt.withCurrency(null)));
        assertMissingField(() -> service.create(validReceipt.withDescription(null)));
    }

    @Test
    public void whenHasInvalidInputOnCreate_throwsError() throws Exception {
        assertIdField("expenseId");
        assertIdField("categoryId");
        assertSpacedField("currency");
        assertSpacedField("description");
    }

    @Test
    public void whenVatRateIsInvalid_throwsError() {
        assertInvalidVatRate(validReceipt.withVatRate(BigDecimal.valueOf(101)));
        assertInvalidVatRate(validReceipt.withVatRate(BigDecimal.valueOf(-1)));
        assertValid(validReceipt.withVatRate(BigDecimal.valueOf(0)));
        assertValid(validReceipt.withVatRate(BigDecimal.valueOf(100)));
    }

    @Test
    public void whenAmountIsInvalid_throwsError() {
        assertInvalidAmount(validReceipt.withAmount(BigDecimal.valueOf(-1)));
        assertInvalidAmount(validReceipt.withAmount(BigDecimal.valueOf(1000001)));
        assertValid(validReceipt.withAmount(BigDecimal.valueOf(0)));
        assertValid(validReceipt.withAmount(BigDecimal.valueOf(1000000)));
    }

    private void assertIdField(String fieldName) throws Exception {
        assertInvalidInput(() -> service.create(withField(validReceipt, fieldName, "A")));
        assertInvalidInput(() -> service.create(withField(validReceipt, fieldName, "")));
        assertDoesNotThrow(() -> service.create(withField(validReceipt, fieldName, "11")));
    }

    private void assertSpacedField(String fieldName) throws Exception {
        assertInvalidInput(() -> service.create(withField(validReceipt, fieldName, "1")));
        assertInvalidInput(() -> service.create(withField(validReceipt, fieldName, "")));
        assertInvalidInput(() -> service.create(withField(validReceipt, fieldName, " ")));
        assertInvalidInput(() -> service.create(withField(validReceipt, fieldName, "a ")));
        assertDoesNotThrow(() -> service.create(withField(validReceipt, fieldName, "A B")));
    }

    private Receipt withField(Receipt receipt, String fieldName, String value) throws Exception {
        Field field = receipt.getClass().getDeclaredField(fieldName);
        field.set(receipt, value);
        return receipt;
    }

    private void assertValid(Receipt receipt) {
        assertDoesNotThrow(() -> service.create(receipt));
    }

    private void assertInvalidVatRate(Receipt receipt) {
        InvalidInputError e = assertThrows(InvalidInputError.class, () -> service.create(receipt));
        assertEquals("Vat rate should be between 0-100.", e.getMessage());
    }

    private void assertInvalidAmount(Receipt receipt) {
        InvalidInputError e = assertThrows(InvalidInputError.class, () -> service.create(receipt));
        assertEquals("Amount should be between 0-1000000.", e.getMessage());
    }

    @Nested
    public class GivenServiceWithOneReceipt {
        @BeforeEach
        public void setup() {
            service.create(validReceipt);
        }

        @Test
        public void listsReceipts() {
            assertEquals(1, service.listBy(new ReceiptQuery()).size());
            assertEquals(validReceipt.withId("0"), service.listBy(new ReceiptQuery()).get(0));
            assertEquals(validReceipt.withId("0"), service.read("0"));
        }

        @Test
        public void listsByExpenseId() {
            assertEquals(0, service.listBy(new ReceiptQuery().withExpenseId("0")).size());
            assertEquals(1, service.listBy(new ReceiptQuery().withExpenseId("1")).size());
            assertEquals(1, service.listBy(new ReceiptQuery()).size());
        }

        @Test
        public void updatesReceiptById() {
            Receipt receipt = service.update("0", validReceipt.withDescription("updated"));
            assertEquals(validReceipt.withId("0").withDescription("updated"), receipt);
            assertEquals(receipt, repo.read("0"));
        }

        @Test
        public void deletesReceiptById() {
            Receipt receipt = service.delete("0");
            assertEquals(validReceipt.withId("0"), receipt);
            assertEquals(0, repo.list().size());
        }

        @Test
        public void whenHasMissingFieldOnUpdate_throwsError(){
            assertMissingField(() -> service.update("0", validReceipt.withExpenseId(null)));
            assertMissingField(() -> service.update("0", validReceipt.withReceiptCategoryId(null)));
            assertMissingField(() -> service.update("0", validReceipt.withReceiptDate(null)));
            assertMissingField(() -> service.update("0", validReceipt.withAmount(null)));
            assertMissingField(() -> service.update("0", validReceipt.withCurrency(null)));
            assertMissingField(() -> service.update("0", validReceipt.withDescription(null)));
        }
    }
}
