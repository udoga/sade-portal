package com.sadesoft.portal.core.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {
    @Test
    public void findsFullName() {
        Employee employee = new Employee("0", "0", "John", "Smith");
        assertEquals("John Smith", employee.getFullName());
    }
}
