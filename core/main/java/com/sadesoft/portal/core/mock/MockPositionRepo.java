package com.sadesoft.portal.core.mock;

import java.util.List;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Position;
import com.sadesoft.portal.core.repo.PositionRepo;

public class MockPositionRepo extends MockRepo<Position> implements PositionRepo {
    public MockPositionRepo() {
        super("Position");
    }

    @Override
    public List<Position> listOptions() {
        return list().stream().map(a -> new Position().withId(a.id).withName(a.name)).toList();
    }
}
