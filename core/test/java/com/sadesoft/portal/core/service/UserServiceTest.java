package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.AuthorizationError;
import com.sadesoft.portal.core.mock.MockPermissionRepo;
import com.sadesoft.portal.core.mock.MockUserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.model.User;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

public class UserServiceTest {
    private String callerId;
    private MockUserRepo userRepo;
    private MockPermissionRepo permissionRepo;
    private UserService service;
    private User validUser;

    @BeforeEach
    public void setup() {
        callerId = "0";
        userRepo = new MockUserRepo();
        permissionRepo = new MockPermissionRepo(callerId, List.of(
                "list_users", "list_user_options", "create_user", "read_user", "update_user", "delete_user"));
        service = new UserService(userRepo, permissionRepo, "");
        validUser = new User(null, "sample-username", "sample-password");
    }

    @Test
    public void createsUser() {
        User user = service.create(callerId, validUser);
        assertEquals(validUser.withId("0").withPassword(null), user);
        assertEquals(1, userRepo.list().size());
        assertEquals(user.username, userRepo.read("0").username);
    }

    @Test
    public void whenCreatesUser_encryptsPassword() {
        service.create(callerId, validUser);
        assertEquals(validUser.findEncryptedPassword(""), userRepo.read("0").password);
    }

    @Test
    public void whenCreatesUser_encryptsPasswordWithSalt() {
        new UserService(userRepo, permissionRepo, "salt").create(callerId, validUser);
        assertEquals(validUser.findEncryptedPassword("salt"), userRepo.read("0").password);
    }

    @Test
    public void whenHasMissingFieldOnCreate_throwsError() {
        assertMissingFieldOnCreate(validUser.withUsername(null));
        assertMissingFieldOnCreate(validUser.withPassword(null));
    }

    private void assertMissingFieldOnCreate(User user) {
        assertThrows(MissingFieldError.class, () -> service.create(callerId, user));
    }

    @Nested
    public class GivenServiceWithOneUser {
        @BeforeEach
        public void setup() {
            service.create(callerId, validUser);
        }

        @Test
        public void listsUsers() {
            User expectedUser = validUser.withId("0").withPassword(null);
            assertEquals(1, service.list(callerId).size());
            assertEquals(expectedUser, service.list(callerId).get(0));
        }

        @Test
        public void listsUserOptions() {
            assertEquals(1, service.listOptions(callerId).size());
            assertNull(service.listOptions(callerId).get(0).password);
        }

        @Test
        public void readsUser() {
            assertEquals(validUser.withId("0").withPassword(null), service.read(callerId, "0"));
        }

        @Test
        public void updatesUserById() {
            User user = service.update(callerId, "0", validUser.withUsername("updated"));
            assertEquals(validUser.withId("0").withUsername("updated").withPassword(null), user);
        }

        @Test
        public void whenUpdatesUser_encryptsPassword() {
            User updatedUser = validUser.withPassword("updated");
            service.update(callerId, "0", updatedUser);
            assertEquals(updatedUser.findEncryptedPassword(""), userRepo.read("0").password);
        }

        @Test
        public void whenUpdatesUser_encryptsPasswordWithSalt() {
            User updatedUser = validUser.withPassword("updated");
            new UserService(userRepo, permissionRepo, "salt").update(callerId, "0", updatedUser);
            assertEquals(updatedUser.findEncryptedPassword("salt"), userRepo.read("0").password);
        }

        @Test
        public void deletesUserById() {
            User user = service.delete(callerId, "0");
            assertEquals(validUser.withId("0").withPassword(null), user);
            assertEquals(0, service.list(callerId).size());
        }

        @Test
        public void whenHasMissingFieldOnUpdate_throwsError() {
            assertMissingFieldOnUpdate(validUser.withUsername(null));
            assertMissingFieldOnUpdate(validUser.withPassword(null));
        }

        @Test
        public void whenCallerIsUnauthorized_throwsError() {
            permissionRepo.authorizedUserId = "";
            assertUnauthorized("list_users", () -> service.list(callerId));
            assertUnauthorized("list_user_options", () -> service.listOptions(callerId));
            assertUnauthorized("create_user", () -> service.create(callerId, validUser));
            assertUnauthorized("read_user", () -> service.read(callerId, "0"));
            assertUnauthorized("update_user", () -> service.update(callerId, "0", validUser));
            assertUnauthorized("delete_user", () -> service.delete(callerId, "0"));
        }

        private void assertUnauthorized(String requestName, Executable executable) {
            AuthorizationError e = assertThrows(AuthorizationError.class, executable);
            assertEquals("Permission denied for '" + requestName + "' request.", e.getMessage());
        }

        private void assertMissingFieldOnUpdate(User user) {
            assertThrows(MissingFieldError.class, () -> service.update(callerId, "0", user));
        }
    }
}
