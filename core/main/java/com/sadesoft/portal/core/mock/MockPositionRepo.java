package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Position;
import com.sadesoft.portal.core.repo.PositionRepo;

public class MockPositionRepo extends MockRepo<Position> implements PositionRepo {
    public MockPositionRepo() {
        super("Position");
    }
}
