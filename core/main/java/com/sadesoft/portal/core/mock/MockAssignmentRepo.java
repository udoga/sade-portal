package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.error.RecordNotFoundError;
import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Assignment;
import com.sadesoft.portal.core.repo.AssignmentRepo;

public class MockAssignmentRepo extends MockRepo<Assignment> implements AssignmentRepo {
    public MockAssignmentRepo() {
        super("Assignment");
    }

    @Override
    public Assignment deleteByValues(Assignment a) {
        return delete(readByValues(a).id);
    }

    private Assignment readByValues(Assignment a) {
        for (Assignment r: records) if (r.withId(null).equals(a.withId(null))) return r;
        throw new RecordNotFoundError(tableName, "record=" + a);
    }
}
