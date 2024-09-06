package com.sadesoft.portal.core.repo;

import java.util.List;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.model.User;

public interface UserRepo extends Repo<User> {
    List<User> listOptions();
}
