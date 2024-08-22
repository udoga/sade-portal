package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.core.filter.ReceiptFilter;
import com.sadesoft.portal.core.mock.MockReceiptRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReceiptServiceTest {
    private MockReceiptRepo repo;
    private ReceiptService service;
    private Receipt validReceipt;

    private void assertValid(Receipt receipt) {
        assertDoesNotThrow(() -> service.create(receipt));
    }

    private void assertMissingField(Receipt receipt) {
        assertThrows(MissingFieldError.class, () -> service.create(receipt));
    }

    private void assertInvalidVatRate(Receipt receipt) {
        InvalidInputError e = assertThrows(InvalidInputError.class, () -> service.create(receipt));
        assertEquals("Vat rate should be between 0-100.", e.getMessage());
    }

    private void assertInvalidAmount(Receipt receipt) {
        InvalidInputError e = assertThrows(InvalidInputError.class, () -> service.create(receipt));
        assertEquals("Amount should be between 0-1000000.", e.getMessage());
    }

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
                (short) 18,
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
        assertMissingField(validReceipt.withExpenseId(null));
        assertMissingField(validReceipt.withReceiptDate(null));
        assertMissingField(validReceipt.withReceiptCategoryId(null));
        assertMissingField(validReceipt.withAmount(null));
        assertMissingField(validReceipt.withVatRate(null));
        assertMissingField(validReceipt.withDescription(null));
    }

    @Test
    public void whenVatRateIsInvalid_throwsError() {
        assertInvalidVatRate(validReceipt.withVatRate((short) 101));
        assertInvalidVatRate(validReceipt.withVatRate((short) -1));
        assertValid(validReceipt.withVatRate((short) 0));
        assertValid(validReceipt.withVatRate((short) 100));
    }

    @Test
    public void whenAmountIsInvalid_throwsError() {
        assertInvalidAmount(validReceipt.withAmount(BigDecimal.valueOf(-1)));
        assertInvalidAmount(validReceipt.withAmount(BigDecimal.valueOf(1000001)));
        assertValid(validReceipt.withAmount(BigDecimal.valueOf(0)));
        assertValid(validReceipt.withAmount(BigDecimal.valueOf(1000000)));
    }

    @Nested
    public class GivenServiceWithOneReceipt {
        @BeforeEach
        public void setup() {
            service.create(validReceipt);
        }

        @Test
        public void listsReceipts() {
            assertEquals(1, service.listBy(new ReceiptFilter()).size());
            assertEquals(validReceipt.withId("0"), service.listBy(new ReceiptFilter()).get(0));
            assertEquals(validReceipt.withId("0"), service.read("0"));
        }

        @Test
        public void listsByExpenseId() {
            assertEquals(0, service.listBy(new ReceiptFilter().withExpenseId("0")).size());
            assertEquals(1, service.listBy(new ReceiptFilter().withExpenseId("1")).size());
            assertEquals(1, service.listBy(new ReceiptFilter()).size());
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
            assertThrows(MissingFieldError.class, () -> service.update("0", validReceipt.withReceiptDate(null)));
            assertThrows(MissingFieldError.class, () -> service.update("0", validReceipt.withReceiptCategoryId(null)));
        }
    }
}
