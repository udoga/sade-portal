package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.mock.MockActivityRepo;
import com.sadesoft.portal.core.model.Activity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActivityServiceTest {
    private MockActivityRepo repo;
    private ActivityService service;
    private Activity validActivity;

    @BeforeEach
    public void setup() {
        repo = new MockActivityRepo();
        service = new ActivityService(repo);
        validActivity = new Activity(null, "Meeting");
    }

    @Nested
    public class GivenServiceWithOneClient {
        @BeforeEach
        public void setup() {
            repo.create(validActivity);
        }

        @Test
        public void listsClients() {
            assertEquals(1, service.list().size());
            assertEquals(validActivity.withId("0"), service.list().get(0));
        }

        @Test
        public void readsClients() {
            assertEquals(validActivity.withId("0"), service.read("0"));
        }

        @Test
        public void updatesClientById() {
            Activity activity = service.update("0", validActivity.withName("updated"));
            assertEquals(validActivity.withId("0").withName("updated"), activity);
            assertEquals(activity, service.read("0"));
        }

        @Test
        public void deletesClientById() {
            Activity activity = service.delete("0");
            assertEquals(validActivity.withId("0"), activity);
            assertEquals(0, service.list().size());
        }
    }
}
