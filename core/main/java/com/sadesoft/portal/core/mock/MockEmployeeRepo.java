package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.detailed.DetailedEmployee;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.repo.EmployeeRepo;
import com.sadesoft.portal.core.repo.UserRepo;

import java.util.List;
import java.util.stream.Collectors;

public class MockEmployeeRepo extends MockRepo<Employee> implements EmployeeRepo {
    private final UserRepo userRepo;

    public MockEmployeeRepo(UserRepo userRepo) {
        super("Employee");
        this.userRepo = userRepo;
    }

    @Override
    public List<Employee> listByUserId(String userId) {
        return records.stream().filter(e -> e.userId.equals(userId)).collect(Collectors.toList());
    }

    @Override
    public List<DetailedEmployee> listWithUser() {
        return records.stream().map(e -> new DetailedEmployee(e).withUser(getUser(e.userId))).toList();
    }

    private User getUser(String userId) {
        return (userRepo == null)? null : userRepo.read(userId);
    }
}
