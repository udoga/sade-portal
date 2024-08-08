package com.sadesoft.portal.core.mock;

import java.util.List;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Activity;
import com.sadesoft.portal.core.repo.ActivityRepo;

public class MockActivityRepo extends MockRepo<Activity> implements ActivityRepo {
    public MockActivityRepo() {
        super("Activity");
    }

    @Override
    public List<Activity> listSummarized() {
        return list().stream().map(a -> new Activity().withId(a.id).withName(a.name)).toList();
    }
}
