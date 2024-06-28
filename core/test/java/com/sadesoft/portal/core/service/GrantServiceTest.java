package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.mock.MockGrantRepo;
import com.sadesoft.portal.core.model.Grant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GrantServiceTest {
    private MockGrantRepo repo;
    private GrantService service;
    private Grant validGrant;

    @BeforeEach
    public void setup() {
        repo = new MockGrantRepo();
        service = new GrantService(repo);
        validGrant = new Grant(null, "0", "0");
    }

    @Nested
    public class GivenServiceWithOneGrant {
        @BeforeEach
        public void setup() {
            repo.create(validGrant);
        }

        @Test
        public void listsGrants() {
            assertEquals(1, service.list().size());
            assertEquals(validGrant.withId("0"), service.list().get(0));
        }

        @Test
        public void deletesGrantById() {
            Grant grant = service.delete("0");
            assertEquals(validGrant.withId("0"), grant);
            assertEquals(0, service.list().size());
        }
    }
}
