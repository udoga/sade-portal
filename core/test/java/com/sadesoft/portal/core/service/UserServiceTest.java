package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.mock.MockUserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.sample.UserSamples;

public class UserServiceTest {
    private MockUserRepo repo;
    private UserService service;
    private User validUser;

    @BeforeEach
    public void setup() {
        repo = new MockUserRepo();
        service = new UserService(repo);
        validUser = UserSamples.validUser.copy();
    }

    @Test
    public void createsUser() {
        User user = service.create(validUser);
        assertEquals(validUser.withId("0"), user);
        assertEquals(1, repo.list().size());
        assertEquals(user, repo.read("0"));
    }

    @Test
    public void whenHasMissingFieldOnCreate_throwsError() {
        assertMissingFieldOnCreate(validUser.withUsername(null));
        assertMissingFieldOnCreate(validUser.withPassword(null));
    }

    private void assertMissingFieldOnCreate(User user) {
        assertThrows(MissingFieldError.class, () -> service.create(user));
    }

    @Nested
    public class GivenServiceWithOneUser {
        @BeforeEach
        public void setup() {
            service.create(validUser);
        }

        @Test
        public void listsUsers() {
            assertEquals(1, service.list().size());
            assertEquals(validUser, service.list().get(0));
            assertEquals(validUser, service.read("0"));
        }

        @Test
        public void updatesUserById() {
            User user = service.update("0", validUser.withUsername("updated"));
            assertEquals(validUser.withId("0").withUsername("updated"), user);
            assertEquals(user, service.read("0"));
        }

        @Test
        public void deletesUserById() {
            User user = service.delete("0");
            assertEquals(validUser.withId("0"), user);
            assertEquals(0, service.list().size());
        }

        @Test
        public void whenHasMissingFieldOnUpdate_throwsError() {
            assertMissingFieldOnUpdate(validUser.withUsername(null));
            assertMissingFieldOnUpdate(validUser.withPassword(null));
        }

        private void assertMissingFieldOnUpdate(User user) {
            assertThrows(MissingFieldError.class, () -> service.update("0", user));
        }
    }
}
