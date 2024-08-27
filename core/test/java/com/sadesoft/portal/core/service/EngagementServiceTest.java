package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.mock.MockEngagementRepo;
import com.sadesoft.portal.core.model.Engagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EngagementServiceTest {
    private MockEngagementRepo repo;
    private EngagementService service;
    private Engagement validEngagement;

    @BeforeEach
    public void setup() {
        repo = new MockEngagementRepo();
        service = new EngagementService(repo);
        validEngagement = new Engagement(null, "0", "0", "0", null, null, "description", "status");
    }

    @Nested
    public class GivenServiceWithOneEngagement {
        @BeforeEach
        public void setup() {
            repo.create(validEngagement);
        }

        @Test
        public void listsActivities() {
            assertEquals(1, service.list().size());
            assertEquals(validEngagement.withId("0"), service.list().get(0));
        }

        @Test
        public void readsEngagement() {
            assertEquals(validEngagement.withId("0"), service.read("0"));
        }

        @Test
        public void updatesEngagementById() {
            Engagement engagement = service.update("0", validEngagement.withDescription("updated"));
            assertEquals(validEngagement.withId("0").withDescription("updated"), engagement);
            assertEquals(engagement, service.read("0"));
        }

        @Test
        public void deletesEngagementById() {
            Engagement engagement = service.delete("0");
            assertEquals(validEngagement.withId("0"), engagement);
            assertEquals(0, service.list().size());
        }
    }
}