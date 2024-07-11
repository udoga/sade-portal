package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.detailed.DetailedVacation;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.Vacation;
import com.sadesoft.portal.core.model.VacationCategory;
import com.sadesoft.portal.core.repo.EmployeeRepo;
import com.sadesoft.portal.core.repo.VacationRepo;
import com.sadesoft.portal.core.repo.VacationCategoryRepo;

import java.util.List;

public class MockVacationRepo extends MockRepo<Vacation> implements VacationRepo {
    private final EmployeeRepo employeeRepo;
    private final VacationCategoryRepo categoryRepo;

    public MockVacationRepo(EmployeeRepo employeeRepo, VacationCategoryRepo categoryRepo) {
        super("Vacation");
        this.employeeRepo = employeeRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<Vacation> listByStatus(String status) {
        return records.stream().filter(v -> v.status.equals(status)).toList();
    }

    @Override
    public List<DetailedVacation> listDetailed() {
        return records.stream().map(v -> new DetailedVacation(v, getEmployee(v.employeeId),
                getCategory(v.categoryId))).toList();
    }

    private Employee getEmployee(String employeeId) {
        return (employeeRepo == null)? null : employeeRepo.read(employeeId);
    }

    private VacationCategory getCategory(String categoryId) {
        return (categoryRepo == null)? null : categoryRepo.read(categoryId);
    }
}
