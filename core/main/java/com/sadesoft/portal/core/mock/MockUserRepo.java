package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.repo.UserRepo;

public class MockUserRepo extends MockRepo<User> implements UserRepo {
    public MockUserRepo() {
        super("User");
    }
}
