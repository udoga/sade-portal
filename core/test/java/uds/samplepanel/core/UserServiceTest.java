package uds.samplepanel.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import uds.samplepanel.base.MemoryTable;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    MemoryTable<User> table;
    UserService service;

    @BeforeEach
    public void setup() {
        table = new MemoryTable<>("User");
        service = new UserService(table);
    }

    @Test
    public void createsUser() {
        User user = service.create(new User("username"));
        assertEquals(1, table.list().size());
        assertEquals(0, user.id);
        assertEquals("username", user.username);
    }

    @Nested
    public class GivenServiceWithOneUser {
        @BeforeEach
        public void setup() {
            table.create(new User("username"));
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
            User user = service.update(0, new User("updated"));
            assertEquals("updated", user.username);
            assertEquals("updated", table.read(0).username);
        }

        @Test
        public void deletesUserById() {
            User user = service.delete(0);
            assertEquals(0, table.list().size());
            assertEquals(0, user.id);
        }
    }
}
