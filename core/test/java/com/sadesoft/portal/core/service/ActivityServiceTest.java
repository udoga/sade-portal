package com.sadesoft.portal.core.service;

import static com.sadesoft.portal.base.util.TestUtil.*;
import com.sadesoft.portal.core.mock.MockActivityRepo;
import com.sadesoft.portal.core.model.Activity;

import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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

    @Test
    public void createsActivity() {
        Activity activity = service.create(validActivity);
        assertEquals(validActivity.withId("0"), activity);
        assertEquals(1, repo.list().size());
        assertEquals(activity.name, repo.read("0").name);
    }

    @Test
    public void whenHasMissingFieldOnCreate_throwsError() {
        assertMissingField(() -> service.create(validActivity.withName(null)));
    }

    @Test
    public void whenHasInvalidInputOnCreate_throwsError() throws Exception {
        assertSpacedField("name");

    }

    private void assertSpacedField(String fieldName) throws Exception {
        assertInvalidInput(() -> service.create(withField(validActivity, fieldName, "1")));
        assertInvalidInput(() -> service.create(withField(validActivity, fieldName, "")));
        assertInvalidInput(() -> service.create(withField(validActivity, fieldName, " ")));
        assertInvalidInput(() -> service.create(withField(validActivity, fieldName, "a ")));
        assertDoesNotThrow(() -> service.create(withField(validActivity, fieldName, "A B")));
    }

    private Activity withField(Activity activity, String fieldName, String value) throws Exception {
        Field field = activity.getClass().getDeclaredField(fieldName);
        field.set(activity, value);
        return activity;
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
        public void listsActivityOptions() {
            assertEquals(repo.listOptions(), service.listOptions());
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

        @Test
        public void whenHasMissingFieldOnUpdate_throwsError() {
            assertMissingField(() -> service.update("0", validActivity.withName(null)));
        }
    }
}
