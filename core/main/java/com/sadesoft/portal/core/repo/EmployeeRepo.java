package com.sadesoft.portal.core.repo;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.model.Employee;

import java.util.List;

public interface EmployeeRepo extends Repo<Employee> {
    List<Employee> listByUserId(String userId);
}
