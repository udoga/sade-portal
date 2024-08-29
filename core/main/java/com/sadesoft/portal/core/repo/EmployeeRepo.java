package com.sadesoft.portal.core.repo;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.detailed.DetailedEmployee;
import com.sadesoft.portal.core.query.EmployeeQuery;
import com.sadesoft.portal.core.model.Employee;

import java.util.List;

public interface EmployeeRepo extends Repo<Employee> {
    List<Employee> listBy(EmployeeQuery query);
    List<DetailedEmployee> listDetailedBy(EmployeeQuery query);
    DetailedEmployee readDetailed(String id);
    List<Employee> listSummarized();
}
