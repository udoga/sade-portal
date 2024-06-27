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
    public class GivenServiceWithOneActivity {
        @BeforeEach
        public void setup() {
            repo.create(validActivity);
        }

        @Test
        public void listsActivities() {
            assertEquals(1, service.list().size());
            assertEquals(validActivity.withId("0"), service.list().get(0));
        }

        @Test
        public void readsActivity() {
            assertEquals(validActivity.withId("0"), service.read("0"));
        }

        @Test
        public void updatesActivityById() {
            Activity activity = service.update("0", validActivity.withName("updated"));
            assertEquals(validActivity.withId("0").withName("updated"), activity);
            assertEquals(activity, service.read("0"));
        }

        @Test
        public void deletesActivityById() {
            Activity activity = service.delete("0");
            assertEquals(validActivity.withId("0"), activity);
            assertEquals(0, service.list().size());
        }
    }
}
