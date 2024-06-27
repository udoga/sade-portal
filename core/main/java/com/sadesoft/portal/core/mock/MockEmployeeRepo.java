package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.error.RecordNotFoundError;
import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.repo.EmployeeRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MockEmployeeRepo extends MockRepo<Employee> implements EmployeeRepo {
    public MockEmployeeRepo() {
        super("Employee");
    }

    @Override
    public List<Employee> listByUserId(String userId) {
        return records.stream().filter(e -> e.userId.equals(userId)).collect(Collectors.toList());
    }
}
