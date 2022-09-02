package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.ListRepo;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.repo.UserRepo;

public class MockUserRepo extends ListRepo<User> implements UserRepo {
    public MockUserRepo() {
        super("User");
    }
}
