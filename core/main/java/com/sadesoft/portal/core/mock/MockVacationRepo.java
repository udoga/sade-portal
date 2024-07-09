package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Vacation;
import com.sadesoft.portal.core.repo.VacationRepo;

import java.util.List;
import java.util.Objects;

public class MockVacationRepo extends MockRepo<Vacation> implements VacationRepo {
    public MockVacationRepo() {
        super("Vacation");
    }

    @Override
    public List<Vacation> listByStatus(String status) {
        return records.stream().filter(v -> v.status.equals(status)).toList();
    }
}
