package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Sector;
import com.sadesoft.portal.core.repo.SectorRepo;

public class MockSectorRepo extends MockRepo<Sector> implements SectorRepo {
    public MockSectorRepo() {
        super("Sector");
    }
}
