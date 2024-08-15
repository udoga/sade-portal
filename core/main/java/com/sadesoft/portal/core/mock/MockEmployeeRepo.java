package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.detailed.DetailedEmployee;
import com.sadesoft.portal.core.filter.EmployeeFilter;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.repo.EmployeeRepo;
import com.sadesoft.portal.core.repo.UserRepo;

import java.util.List;

public class MockEmployeeRepo extends MockRepo<Employee> implements EmployeeRepo {
    private final UserRepo userRepo;

    public MockEmployeeRepo(UserRepo userRepo) {
        super("Employee");
        this.userRepo = userRepo;
    }

    @Override
    public List<Employee> listBy(EmployeeFilter filter) {
        return records.stream().filter(e -> e.passes(filter)).toList();
    }

    @Override
    public List<DetailedEmployee> listDetailedBy(EmployeeFilter filter) {
        return listBy(filter).stream().map(e -> new DetailedEmployee(e, getUser(e.userId))).toList();
    }

    @Override
    public DetailedEmployee readDetailed(String id) {
        return new DetailedEmployee(read(id), getUser(read(id).userId));
    }

    @Override
    public List<Employee> listSummarized() {
        return list().stream()
                .map(e -> new Employee().withId(e.id).withFirstName(e.firstName).withLastName(e.lastName)).toList();
    }

    private User getUser(String userId) {
        return (userRepo == null)? null : userRepo.read(userId);
    }
}
