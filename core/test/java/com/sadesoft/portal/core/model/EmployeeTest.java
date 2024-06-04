package com.sadesoft.portal.core.model;

import com.sadesoft.portal.core.sample.EmployeeSamples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EmployeeTest {
    private Employee validEmployee;

    @BeforeEach
    public void setup() {
        validEmployee = EmployeeSamples.validEmployee.copy();
    }

    @Test
    public void initializesWithoutFields() {
        Employee employee = new Employee();
        assertNull(employee.id);
        assertNull(employee.userId);
        assertNull(employee.firstName);
        assertNull(employee.lastName);
    }

    @Test
    public void printsFields() {
        assertEquals("Employee[id=null" +
                " userId=1" +
                " firstName=John" +
                " lastName=Green" +
                "]", validEmployee.toString());
    }

    @Test
    public void clonesWithFields() {
        assertNull(validEmployee.withId(null).id);
        assertNull(validEmployee.withUserId(null).userId);
        assertNull(validEmployee.withFirstName(null).firstName);
        assertNull(validEmployee.withLastName(null).lastName);
    }

    @Test
    public void checksEquality() {
        assertEquals(EmployeeSamples.validEmployee, validEmployee.copy());
        assertNotEquals(EmployeeSamples.validEmployee, validEmployee.withId("0"));
        assertNotEquals(EmployeeSamples.validEmployee, validEmployee.withUserId(null));
        assertNotEquals(EmployeeSamples.validEmployee, validEmployee.withFirstName(null));
        assertNotEquals(EmployeeSamples.validEmployee, validEmployee.withLastName(null));
    }
}
