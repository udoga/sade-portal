package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.MissingFieldError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.sadesoft.portal.base.repo.ListRepo;
import com.sadesoft.portal.core.model.User;
import org.junit.jupiter.api.function.Executable;

public class UserServiceTest {
    ListRepo<User> repo;
    UserService service;

    @BeforeEach
    public void setup() {
        repo = new ListRepo<>("User");
        service = new UserService(repo);
    }

    @Test
    public void createsUser() {
        User user = service.create(new User("username", "fullName"));
        assertEquals(1, repo.list().size());
        assertEquals(0, user.id);
        assertEquals("username", user.username);
    }

    @Test
    public void whenCreatesUserWithoutUsername_throwsError() {
        User user = new User(null, "name");
        assertThrows(MissingFieldError.class, () -> service.create(user));
    }

    @Test
    public void whenCreatesUserWithoutFullName_throwsError() {
        User user = new User("name", null);
        assertThrows(MissingFieldError.class, () -> service.create(user));
    }

    @Test
    public void whenCreatesUserWithEmptyUsername_throwsError() {
        User user = new User("", "fullName");
        assertThrows(MissingFieldError.class, () -> service.create(user));
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
            assertThrows(MissingFieldError.class, () -> service.update(0, new User(null, "fullName")));
        }

        @Test
        public void whenUpdatesUserWithoutFullName_throwsError() {
            assertThrows(MissingFieldError.class, () -> service.update(0, new User("username", null)));
        }
    }
}
