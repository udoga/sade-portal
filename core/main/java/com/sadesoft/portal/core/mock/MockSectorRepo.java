package com.sadesoft.portal.core.mock;

import java.util.List;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Sector;
import com.sadesoft.portal.core.repo.SectorRepo;

public class MockSectorRepo extends MockRepo<Sector> implements SectorRepo {
    public MockSectorRepo() {
        super("Sector");
    }

    @Override
    public List<Sector> listOptions() {
        return list().stream().map(s -> new Sector().withId(s.id).withName(s.name)).toList();
    }
}
