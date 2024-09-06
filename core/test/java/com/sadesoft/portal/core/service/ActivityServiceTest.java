package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.mock.MockActivityRepo;
import com.sadesoft.portal.core.model.Activity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertMissingFieldOnCreate(validActivity.withName(null));
    }

    @Test
    public void whenHasInvalidInputOnCreate_throwsError() {
        assertInvalidInputOnCreate(validActivity.withName("1"));
        assertInvalidInputOnCreate(validActivity.withName(""));
        assertDoesNotThrow(() -> service.create(validActivity.withName("A B")));

    }

    private void assertMissingFieldOnCreate(Activity activity) {
        assertThrows(MissingFieldError.class, () -> service.create(activity));
    }

    private void assertInvalidInputOnCreate(Activity activity) {
        assertThrows(InvalidInputError.class, () -> service.create(activity));
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
            assertMissingFieldOnUpdate(validActivity.withName(null));
        }

        @Test
        public void whenHasInvalidInputOnUpdate_throwsError() {
            assertInvalidInputOnUpdate(validActivity.withName("1"));
            assertInvalidInputOnUpdate(validActivity.withName(""));
            assertDoesNotThrow(() -> service.update("0", validActivity.withName("A B")));
        }

        private void assertMissingFieldOnUpdate(Activity activity) {
            assertThrows(MissingFieldError.class, () -> service.update("0", activity));
        }

        private void assertInvalidInputOnUpdate(Activity activity) {
            assertThrows(InvalidInputError.class, () -> service.update("0", activity));
        }
    }
}
