package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.mock.MockPositionRepo;
import com.sadesoft.portal.core.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PositionServiceTest {
    private MockPositionRepo repo;
    private PositionService service;
    private Position validPosition;

    @BeforeEach
    public void setup() {
        repo = new MockPositionRepo();
        service = new PositionService(repo);
        validPosition = new Position(null, "Manager");
    }

        @Test
    public void whenHasMissingFieldOnCreate_throwsError() {
        assertMissingFieldOnCreate(validPosition.withName(null));
    }

    @Test
    public void whenHasInvalidInputOnCreate_throwsError() {
        assertInvalidInputOnCreate(validPosition.withName("1"));
        assertInvalidInputOnCreate(validPosition.withName(""));
        assertDoesNotThrow(() -> service.create(validPosition.withName("A B")));

    }

    private void assertMissingFieldOnCreate(Position position) {
        assertThrows(MissingFieldError.class, () -> service.create(position));
    }

    private void assertInvalidInputOnCreate(Position position) {
        assertThrows(InvalidInputError.class, () -> service.create(position));
    }

    @Nested
    public class GivenServiceWithOnePosition {
        @BeforeEach
        public void setup() {
            repo.create(validPosition);
        }

        @Test
        public void listsPositions() {
            assertEquals(1, service.list().size());
            assertEquals(validPosition.withId("0"), service.list().get(0));
        }

        @Test
        public void listsPositionOptions() {
            assertEquals(repo.listOptions(), service.listOptions());
        }

        @Test
        public void readsPosition() {
            assertEquals(validPosition.withId("0"), service.read("0"));
        }

        @Test
        public void updatesPositionById() {
            Position position = service.update("0", validPosition.withName("updated"));
            assertEquals(validPosition.withId("0").withName("updated"), position);
            assertEquals(position, service.read("0"));
        }

        @Test
        public void deletesPositionById() {
            Position position = service.delete("0");
            assertEquals(validPosition.withId("0"), position);
            assertEquals(0, service.list().size());
        }

        @Test
        public void whenHasMissingFieldOnUpdate_throwsError() {
            assertMissingFieldOnUpdate(validPosition.withName(null));
        }

        @Test
        public void whenHasInvalidInputOnUpdate_throwsError() {
            assertInvalidInputOnUpdate(validPosition.withName("1"));
            assertInvalidInputOnUpdate(validPosition.withName(""));
            assertDoesNotThrow(() -> service.update("0", validPosition.withName("A B")));
        }

        private void assertMissingFieldOnUpdate(Position position) {
            assertThrows(MissingFieldError.class, () -> service.update("0", position));
        }

        private void assertInvalidInputOnUpdate(Position position) {
            assertThrows(InvalidInputError.class, () -> service.update("0", position));
        }
    }
}
