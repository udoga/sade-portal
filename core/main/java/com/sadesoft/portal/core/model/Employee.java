package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;

import java.util.Objects;

public class Employee implements Record {
    public String id = null;
    public String userId = null;
    public String firstName = null;
    public String lastName = null;

    public Employee(String id, String userId, String firstName, String lastName) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee() {
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Employee)) return false;
        Employee e = (Employee) o;
        return Objects.equals(id, e.id) &&
                Objects.equals(userId, e.userId) &&
                Objects.equals(firstName, e.firstName) &&
                Objects.equals(lastName, e.lastName);
    }

    @Override
    public String toString() {
        return "Employee[" +
                "id=" + id +
                " userId=" + userId +
                " firstName=" + firstName +
                " lastName=" + lastName +
                "]";
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Employee copy() {
        try {
            return (Employee) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Employee withId(String id) {
        Employee employee = copy();
        employee.id = id;
        return employee;
    }

    public Employee withUserId(String userId) {
        Employee employee = copy();
        employee.userId = userId;
        return employee;
    }

    public Employee withFirstName(String firstName) {
        Employee employee = copy();
        employee.firstName = firstName;
        return employee;
    }

    public Employee withLastName(String lastName) {
        Employee employee = copy();
        employee.lastName = lastName;
        return employee;
    }
}
