package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.detailed.DetailedEmployee;
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
    public List<Employee> listBy(String userId) {
        return records.stream().filter(e -> e.matches(userId)).toList();
    }

    @Override
    public List<DetailedEmployee> listDetailed() {
        return records.stream().map(e -> new DetailedEmployee(e, getUser(e.userId))).toList();
    }

    @Override
    public DetailedEmployee readDetailed(String id) {
        return new DetailedEmployee(read(id), getUser(read(id).userId));
    }

    private User getUser(String userId) {
        return (userRepo == null)? null : userRepo.read(userId);
    }
}
