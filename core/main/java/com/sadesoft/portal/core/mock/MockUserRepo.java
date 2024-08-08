package com.sadesoft.portal.core.mock;

import java.util.List;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.repo.UserRepo;

public class MockUserRepo extends MockRepo<User> implements UserRepo {
    public MockUserRepo() {
        super("User");
    }

    @Override
    public List<User> listSummarized() {
        return list().stream().map(u -> new User().withId(u.id).withUsername(u.username)).toList();
    }
}
