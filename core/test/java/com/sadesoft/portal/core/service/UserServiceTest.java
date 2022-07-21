package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.MissingFieldError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.sadesoft.portal.base.repo.ListRepo;
import com.sadesoft.portal.core.model.User;

public class UserServiceTest {
    ListRepo<User> repo;
    UserService service;
    User validUser;

    @BeforeEach
    public void setup() {
        repo = new ListRepo<>("User");
        service = new UserService(repo);
        validUser = new User("username", "full name");
    }

    @Test
    public void createsUser() {
        User user = service.create(validUser);
        assertEquals(1, repo.list().size());
        assertEquals(0, user.id);
        assertEquals("username", user.username);
    }

    @Test
    public void whenCreatesUserWithoutUsername_throwsError() {
        assertThrows(MissingFieldError.class, () -> service.create(validUser.withUsername(null)));
    }

    @Test
    public void whenCreatesUserWithoutFullName_throwsError() {
        assertThrows(MissingFieldError.class, () -> service.create(validUser.withFullName(null)));
    }

    @Test
    public void whenCreatesUserWithEmptyUsername_throwsError() {
        assertThrows(MissingFieldError.class, () -> service.create(validUser.withUsername("")));
    }

    @Nested
    public class GivenServiceWithOneUser {
        @BeforeEach
        public void setup() {
            repo.create(new User("username", "fullName"));
        }

        @Test
        public void listsUsers() {
            assertEquals(1, service.list().size());
            assertEquals("username", service.list().get(0).username);
        }

        @Test
        public void readsUserById() {
            assertEquals("username", service.read(0).username);
        }

        @Test
        public void updatesUserById() {
            User user = service.update(0, new User("updated", "fullName"));
            assertEquals("updated", user.username);
            assertEquals("updated", repo.read(0).username);
        }

        @Test
        public void deletesUserById() {
            User user = service.delete(0);
            assertEquals(0, repo.list().size());
            assertEquals(0, user.id);
        }

        @Test
        public void whenUpdatesUserWithoutUsername_throwsError() {
            assertThrows(MissingFieldError.class, () -> service.update(0, validUser.withUsername(null)));
        }

        @Test
        public void whenUpdatesUserWithoutFullName_throwsError() {
            assertThrows(MissingFieldError.class, () -> service.update(0, validUser.withFullName(null)));
        }
    }
}
