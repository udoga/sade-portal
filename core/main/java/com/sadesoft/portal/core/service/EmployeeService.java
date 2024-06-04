package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.repo.EmployeeRepo;

import java.util.List;

public class EmployeeService {
    private final EmployeeRepo repo;

    public EmployeeService(EmployeeRepo repo) {
        this.repo = repo;
    }

    public List<Employee> list() {
        return repo.list();
    }

    public List<Employee> listPage(int pageNo, int pageSize) {
        return repo.listPage(pageNo, pageSize);
    }

    public Employee read(String id) {
        return repo.read(id);
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
        checkMissingField(employee.userId, "userId");
        checkMissingField(employee.firstName, "firstName");
        checkMissingField(employee.lastName, "lastName");
    }

    private void checkMissingField(String value, String field) {
        if (value == null) throw new MissingFieldError(field);
    }
}
