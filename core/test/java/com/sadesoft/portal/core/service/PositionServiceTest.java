package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.mock.MockPositionRepo;
import com.sadesoft.portal.core.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Nested
    public class GivenServiceWithOnePosition {
        @BeforeEach
        public void setup() {
            repo.create(validPosition);
        }

        @Test
        public void listsActivities() {
            assertEquals(1, service.list().size());
            assertEquals(validPosition.withId("0"), service.list().get(0));
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
    }
}
