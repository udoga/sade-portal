package com.sadesoft.portal.core.repo;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.detailed.DetailedEmployee;
import com.sadesoft.portal.core.filter.EmployeeFilter;
import com.sadesoft.portal.core.model.Employee;

import java.util.List;

public interface EmployeeRepo extends Repo<Employee> {
    List<Employee> listBy(EmployeeFilter filter);
    List<DetailedEmployee> listDetailedBy(EmployeeFilter filter);
    DetailedEmployee readDetailed(String id);
    List<Employee> listSummarized();
}
