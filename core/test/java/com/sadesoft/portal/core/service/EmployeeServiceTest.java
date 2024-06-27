package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.mock.MockEmployeeRepo;
import com.sadesoft.portal.core.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {
    private MockEmployeeRepo repo;
    private EmployeeService service;
    private Employee validEmployee;

    @BeforeEach
    public void setup() {
        repo = new MockEmployeeRepo();
        service = new EmployeeService(repo);
        validEmployee = new Employee(null, "1", "John", "Green");
    }

    @Test
    public void createsEmployee() {
        Employee employee = service.create(validEmployee);
        assertEquals(validEmployee.withId("0"), employee);
        assertEquals(1, repo.list().size());
        assertEquals(employee.firstName, repo.read("0").firstName);
    }

    @Test
    public void whenHasMissingFieldOnCreate_throwsError() {
        assertMissingFieldOnCreate(validEmployee.withUserId(null));
        assertMissingFieldOnCreate(validEmployee.withFirstName(null));
        assertMissingFieldOnCreate(validEmployee.withLastName(null));
    }

    private void assertMissingFieldOnCreate(Employee employee) {
        assertThrows(MissingFieldError.class, () -> service.create(employee));
    }

    @Nested
    public class GivenServiceWithOneEmployee {
        @BeforeEach
        public void setup() {
            service.create(validEmployee);
        }

        @Test
        public void listsEmployees() {
            assertEquals(1, service.list().size());
            assertEquals(validEmployee, service.list().get(0));
            assertEquals(validEmployee, service.read("0"));
        }

        @Test
        public void listsByUserId() {
            assertEquals(0, service.listByUserId("0").size());
            assertEquals(1, service.listByUserId("1").size());
            assertEquals(1, service.listByUserId(null).size());
        }

        @Test
        public void updatesEmployeeById() {
            Employee employee = service.update("0", validEmployee.withFirstName("updated"));
            assertEquals(validEmployee.withId("0").withFirstName("updated"), employee);
            assertEquals(employee, service.read("0"));
        }

        @Test
        public void deletesEmployeeById() {
            Employee employee = service.delete("0");
            assertEquals(validEmployee.withId("0"), employee);
            assertEquals(0, service.list().size());
        }

        @Test
        public void whenHasMissingFieldOnUpdate_throwsError() {
            assertMissingFieldOnUpdate(validEmployee.withUserId(null));
            assertMissingFieldOnUpdate(validEmployee.withFirstName(null));
            assertMissingFieldOnUpdate(validEmployee.withLastName(null));
        }

        private void assertMissingFieldOnUpdate(Employee employee) {
            assertThrows(MissingFieldError.class, () -> service.update("0", employee));
        }
    }
}
