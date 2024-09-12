package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.detailed.DetailedEmployee;
import com.sadesoft.portal.core.query.EmployeeQuery;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.repo.EmployeeRepo;

import java.util.List;
import java.util.regex.Pattern;

public class EmployeeService {
    private final EmployeeRepo repo;

    public EmployeeService(EmployeeRepo repo) {
        this.repo = repo;
    }

    public List<Employee> listBy(EmployeeQuery query) {
        return repo.listBy(query);
    }

    public List<DetailedEmployee> listDetailedBy(EmployeeQuery query) {
        return repo.listDetailedBy(query);
    }

    public List<Employee> listOptions() {
        return repo.listOptions();
    }

    public Employee read(String id) {
        return repo.read(id);
    }

    public DetailedEmployee readDetailed(String id) {
        return repo.readDetailed(id);
    }

    public Employee create(Employee employee) {
        check(employee);
        return repo.create(employee);
    }

    public Employee update(String id, Employee employee) {
        check(employee);
        return repo.update(id, employee);
    }

    public Employee delete(String id) {
        return repo.delete(id);
    }

    private void check(Employee employee) {
        checkMissingFields(employee);
        checkFieldFormats(employee);
    }

    private void checkMissingFields(Employee employee) {
        checkMissingField(employee.userId, "userId");
        checkMissingField(employee.firstName, "firstName");
        checkMissingField(employee.lastName, "lastName");
    }

    private void checkFieldFormats(Employee employee) {
        checkFieldFormat(employee.userId, "userId", "^[0-9]+$");
        checkFieldFormat(employee.firstName, "firstName", "^[a-zA-Z]+( [a-zA-Z]+)*$");
        checkFieldFormat(employee.lastName, "lastName", "^[a-zA-Z]+( [a-zA-Z]+)*$");
    }

    private void checkMissingField(String value, String field) {
        if (value == null) throw new MissingFieldError(field);
    }

    private void checkFieldFormat(String value, String field, String regex) {
        if (!Pattern.matches(regex, value)) throw new InvalidInputError("Format of field '" + field + "' is invalid.");
    }
}
