package com.sadesoft.portal.core.service;

import static com.sadesoft.portal.base.util.TestUtil.*;
import com.sadesoft.portal.core.mock.MockPositionRepo;
import com.sadesoft.portal.core.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;

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
        assertMissingField(() -> service.create(validPosition.withName(null)));
    }

    @Test
    public void whenHasInvalidInputOnCreate_throwsError() throws Exception {
        assertSpacedField("name");
    }

    private void assertSpacedField(String fieldName) throws Exception {
        assertInvalidInput(() -> service.create(withField(validPosition, fieldName, "1")));
        assertInvalidInput(() -> service.create(withField(validPosition, fieldName, "")));
        assertInvalidInput(() -> service.create(withField(validPosition, fieldName, " ")));
        assertInvalidInput(() -> service.create(withField(validPosition, fieldName, "a ")));
        assertDoesNotThrow(() -> service.create(withField(validPosition, fieldName, "A B")));
    }

    private Position withField(Position position, String fieldName, String value) throws Exception {
        Field field = position.getClass().getDeclaredField(fieldName);
        field.set(position, value);
        return position;
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
            assertMissingField(() -> service.update("0", validPosition.withName(null)));
        }
    }
}
