package com.sadesoft.portal.core.service;

import static com.sadesoft.portal.base.util.TestUtil.*;
import com.sadesoft.portal.core.query.EmployeeQuery;
import com.sadesoft.portal.core.mock.MockEmployeeRepo;
import com.sadesoft.portal.core.mock.MockUserRepo;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {
    private MockUserRepo userRepo;
    private MockEmployeeRepo employeeRepo;
    private EmployeeService service;
    private Employee validEmployee;

    @BeforeEach
    public void setup() {
        userRepo = new MockUserRepo();
        employeeRepo = new MockEmployeeRepo(userRepo);
        service = new EmployeeService(employeeRepo);
        validEmployee = new Employee(null, "0", "John", "Green");
    }

    @Test
    public void createsEmployee() {
        Employee employee = service.create(validEmployee);
        assertEquals(validEmployee.withId("0"), employee);
        assertEquals(1, employeeRepo.list().size());
        assertEquals(employee.firstName, employeeRepo.read("0").firstName);
    }

    @Test
    public void whenHasMissingFieldOnCreate_throwsError() {
        assertMissingField(() -> service.create(validEmployee.withUserId(null)));
        assertMissingField(() -> service.create(validEmployee.withFirstName(null)));
        assertMissingField(() -> service.create(validEmployee.withLastName(null)));
    }

    @Test
    public void whenHasInvalidInputOnCreate_throwsError() throws Exception{
        assertIdField("userId");
        assertSpacedField("firstName");
        assertSpacedField("lastName");
    }

    private void assertIdField(String fieldName) throws Exception {
        assertInvalidInput(() -> service.create(withField(validEmployee, fieldName, "A")));
        assertInvalidInput(() -> service.create(withField(validEmployee, fieldName, "")));
        assertDoesNotThrow(() -> service.create(withField(validEmployee, fieldName, "11")));
    }

    private void assertSpacedField(String fieldName) throws Exception {
        assertInvalidInput(() -> service.create(withField(validEmployee, fieldName, "1")));
        assertInvalidInput(() -> service.create(withField(validEmployee, fieldName, "")));
        assertInvalidInput(() -> service.create(withField(validEmployee, fieldName, " ")));
        assertInvalidInput(() -> service.create(withField(validEmployee, fieldName, "a ")));
        assertDoesNotThrow(() -> service.create(withField(validEmployee, fieldName, "A B")));
        assertDoesNotThrow(() -> service.create(withField(validEmployee, fieldName, "ğ")));
    }

    private Employee withField(Employee employee, String fieldName, String value) throws Exception {
        Field field = employee.getClass().getDeclaredField(fieldName);
        field.set(employee, value);
        return employee;
    }

    @Nested
    public class GivenServiceWithOneEmployee {
        @BeforeEach
        public void setup() {
            service.create(validEmployee);
        }

        @Test
        public void listsEmployees() {
            List<Employee> employees = service.listBy(new EmployeeQuery());
            assertEquals(1, employees.size());
            assertEquals(validEmployee.withId("0"), employees.get(0));
            assertEquals(validEmployee.withId("0"), service.read("0"));
        }

        @Test
        public void listsEmployeeOptions() {
            assertEquals(1, service.listOptions().size());
            assertNull(service.listOptions().get(0).userId);
        }

        @Test
        public void listsByOffset() {
            employeeRepo.create(validEmployee.copy());
            assertEquals(2, service.listBy(new EmployeeQuery().withOffset(0)).size());
            assertEquals(1, service.listBy(new EmployeeQuery().withOffset(1)).size());
            assertEquals(0, service.listBy(new EmployeeQuery().withOffset(2)).size());
        }

        @Test
        public void listsByUserId() {
            assertEquals(1, service.listBy(new EmployeeQuery().withUserId("0")).size());
            assertEquals(0, service.listBy(new EmployeeQuery().withUserId("1")).size());
        }

        @Test
        public void listsDetailedEmployees() {
            userRepo.create(new User());
            assertEquals(employeeRepo.listDetailedBy(new EmployeeQuery()), service.listDetailedBy(new EmployeeQuery()));
        }

        @Test
        public void listsDetailedByOffset() {
            userRepo.create(new User());
            employeeRepo.create(validEmployee.copy());
            assertEquals(2, service.listDetailedBy(new EmployeeQuery().withOffset(0)).size());
            assertEquals(1, service.listDetailedBy(new EmployeeQuery().withOffset(1)).size());
            assertEquals(0, service.listDetailedBy(new EmployeeQuery().withOffset(2)).size());
        }

        @Test
        public void readsEmployeeWithUser() {
            userRepo.create(new User(null, "username", "pwd"));
            assertEquals(employeeRepo.readDetailed("0"), service.readDetailed("0"));
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
            assertEquals(0, employeeRepo.list().size());
        }

        @Test
        public void whenHasMissingFieldOnUpdate_throwsError() {
            assertMissingField(() -> service.update("0", validEmployee.withUserId(null)));
            assertMissingField(() -> service.update("0", validEmployee.withFirstName(null)));
            assertMissingField(() -> service.update("0", validEmployee.withLastName(null)));
        }
    }
}
