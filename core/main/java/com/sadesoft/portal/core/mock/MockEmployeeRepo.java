package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.repo.EmployeeRepo;

public class MockEmployeeRepo extends MockRepo<Employee> implements EmployeeRepo {
    public MockEmployeeRepo() {
        super("Employee");
    }
}
