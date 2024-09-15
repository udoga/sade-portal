package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.AuthorizationError;
import com.sadesoft.portal.base.auth.MockAuthorizer;
import com.sadesoft.portal.core.mock.MockUserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.sadesoft.portal.base.util.TestUtil.assertMissingField;
import static org.junit.jupiter.api.Assertions.*;

import com.sadesoft.portal.core.model.User;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

public class UserServiceTest {
    private String caller0;
    private MockUserRepo userRepo;
    private MockAuthorizer authorizer;
    private UserService service;
    private User validUser;

    @BeforeEach
    public void setup() {
        caller0 = "0";
        userRepo = new MockUserRepo();
        authorizer = new MockAuthorizer();
        service = new UserService(userRepo, authorizer, "");
        validUser = new User(null, "sample-username", "sample-password");
    }

    private void assertUnauthorized(String permissionName, Executable executable) {
        AuthorizationError e = assertThrows(AuthorizationError.class, executable);
        assertEquals(permissionName, e.permissionName);
    }

    private void assertUnauthorizedForAll(String callerId) {
        assertUnauthorized("user_list", () -> service.list(callerId));
        assertUnauthorized("user_list_options", () -> service.listOptions(callerId));
        assertUnauthorized("user_create", () -> service.create(callerId, validUser));
        assertUnauthorized("user_read", () -> service.read(callerId, "0"));
        assertUnauthorized("user_update", () -> service.update(callerId, "0", validUser));
        assertUnauthorized("user_delete", () -> service.delete(callerId, "0"));
    }

    @Test
    public void whenCallerIdIsNullOrUnauthorized_throwsError() {
        userRepo.create(validUser);
        assertUnauthorizedForAll(null);
        assertUnauthorizedForAll("1");
    }

    @Nested
    public class GivenServiceWithOneUserAndNoAuthorization {
        @BeforeEach
        public void setup() {
            userRepo.create(validUser);
            authorizer.enabled = false;
        }

        @Test
        public void createsUser() {
            User user = service.create(caller0, validUser);
            assertEquals(validUser.withId("1").withPassword(null), user);
            assertEquals(2, userRepo.list().size());
            assertEquals(user.username, userRepo.read("0").username);
        }

        @Test
        public void whenCreatesUser_encryptsPassword() {
            service.create(caller0, validUser);
            assertEquals(validUser.findEncryptedPassword(""), userRepo.read("1").password);
        }

        @Test
        public void whenCreatesUser_encryptsPasswordWithSalt() {
            new UserService(userRepo, authorizer, "salt").create(caller0, validUser);
            assertEquals(validUser.findEncryptedPassword("salt"), userRepo.read("1").password);
        }

        @Test
        public void whenHasMissingFieldOnCreate_throwsError() {
            assertMissingField(() -> service.create(caller0, validUser.withUsername(null)));
            assertMissingField(() -> service.create(caller0, validUser.withPassword(null)));
        }

        @Test
        public void listsUsers() {
            User expectedUser = validUser.withId("0").withPassword(null);
            assertEquals(1, service.list(caller0).size());
            assertEquals(expectedUser, service.list(caller0).get(0));
        }

        @Test
        public void listsUserOptions() {
            assertEquals(1, service.listOptions(caller0).size());
            assertNull(service.listOptions(caller0).get(0).password);
        }

        @Test
        public void readsUser() {
            userRepo.create(validUser);
            assertEquals(validUser.withId("0").withPassword(null), service.read(caller0, "0"));
            assertEquals(validUser.withId("1").withPassword(null), service.read(caller0, "1"));
        }

        @Test
        public void updatesUserById() {
            User user = service.update(caller0, "0", validUser.withUsername("updated"));
            assertEquals(validUser.withId("0").withUsername("updated").withPassword(null), user);
        }

        @Test
        public void whenUpdatesUser_encryptsPassword() {
            User updatedUser = validUser.withPassword("updated");
            service.update(caller0, "0", updatedUser);
            assertEquals(updatedUser.findEncryptedPassword(""), userRepo.read("0").password);
        }

        @Test
        public void whenUpdatesUser_encryptsPasswordWithSalt() {
            User updatedUser = validUser.withPassword("updated");
            new UserService(userRepo, authorizer, "salt").update(caller0, "0", updatedUser);
            assertEquals(updatedUser.findEncryptedPassword("salt"), userRepo.read("0").password);
        }

        @Test
        public void whenHasMissingFieldOnUpdate_throwsError() {
            assertMissingField(() -> service.update(caller0, "0", validUser.withUsername(null)));
            assertMissingField(() -> service.update(caller0, "0", validUser.withPassword(null)));
        }

        @Test
        public void deletesUserById() {
            User user = service.delete(caller0, "0");
            assertEquals(validUser.withId("0").withPassword(null), user);
            assertEquals(0, service.list(caller0).size());
        }
    }

    @Nested
    public class GivenServiceWithTwoUsersAndEmployeePermissions {
        private final String caller1 = "1";

        @BeforeEach
        public void setup() {
            userRepo.create(validUser.withUsername("username0"));
            userRepo.create(validUser.withUsername("username1"));
            authorizer.callerIds.addAll(List.of(caller0, caller1));
            authorizer.permissionNames.addAll(List.of(
                    "user_list",
                    "user_list_options",
                    "user_read",
                    "user_update"));
        }

        @Test
        public void whenReadsOtherUser_throwsError() {
            assertDoesNotThrow(() -> service.read(caller0, "0"));
            assertUnauthorized("user_read-user:other", () -> service.read(caller0, "1"));
            assertUnauthorized("user_read-user:other", () -> service.read(caller1, "0"));
            assertDoesNotThrow(() -> service.read(caller1, "1"));
        }

        @Test
        public void whenUpdatesOtherUser_throwsError() {
            assertUnauthorized("user_update-user:other", () -> service.update(caller0, "1", validUser));
        }

        @Test
        public void whenUpdatesOwnUsername_throwsError() {
            String permissionName = "user_update-username_change";
            assertUnauthorized(permissionName, () -> service.update(caller0, "0", validUser.withUsername("username1")));
            assertUnauthorized(permissionName, () -> service.update(caller1, "1", validUser.withUsername("username0")));
            assertDoesNotThrow(() -> service.update(caller0, "0", validUser.withUsername("username0")));
            assertDoesNotThrow(() -> service.update(caller1, "1", validUser.withUsername("username1")));
        }
    }
}
