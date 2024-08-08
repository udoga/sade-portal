package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.mock.MockUserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.model.User;

public class UserServiceTest {
    private MockUserRepo repo;
    private UserService service;
    private User validUser;

    @BeforeEach
    public void setup() {
        repo = new MockUserRepo();
        service = new UserService(repo, "");
        validUser = new User(null, "sample-username", "sample-password");
    }

    @Test
    public void createsUser() {
        User user = service.create(validUser);
        assertEquals(validUser.withId("0").withPassword(null), user);
        assertEquals(1, repo.list().size());
        assertEquals(user.username, repo.read("0").username);
    }

    @Test
    public void whenCreatesUser_encryptsPassword() {
        service.create(validUser);
        assertEquals(validUser.findEncryptedPassword(""), repo.read("0").password);
    }

    @Test
    public void whenCreatesUser_encryptsPasswordWithSalt() {
        new UserService(repo, "salt").create(validUser);
        assertEquals(validUser.findEncryptedPassword("salt"), repo.read("0").password);
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
            User expectedUser = validUser.withId("0").withPassword(null);
            assertEquals(1, service.list().size());
            assertEquals(expectedUser, service.list().get(0));
            assertEquals(expectedUser, service.read("0"));
        }

        @Test
        public void listSummarizedUsers() {
            assertEquals(1, service.listSummarized().size());
            assertEquals(null, service.listSummarized().get(0).password);
        }

        @Test
        public void updatesUserById() {
            User user = service.update("0", validUser.withUsername("updated"));
            assertEquals(validUser.withId("0").withUsername("updated").withPassword(null), user);
            assertEquals(user, service.read("0"));
        }

        @Test
        public void whenUpdatesUser_encryptsPassword() {
            User updatedUser = validUser.withPassword("updated");
            service.update("0", updatedUser);
            assertEquals(updatedUser.findEncryptedPassword(""), repo.read("0").password);
        }

        @Test
        public void whenUpdatesUser_encryptsPasswordWithSalt() {
            User updatedUser = validUser.withPassword("updated");
            new UserService(repo, "salt").update("0", updatedUser);
            assertEquals(updatedUser.findEncryptedPassword("salt"), repo.read("0").password);
        }

        @Test
        public void deletesUserById() {
            User user = service.delete("0");
            assertEquals(validUser.withId("0").withPassword(null), user);
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
