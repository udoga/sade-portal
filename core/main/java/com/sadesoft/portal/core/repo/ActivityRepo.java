package com.sadesoft.portal.core.repo;

import java.util.List;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.model.Activity;

public interface ActivityRepo extends Repo<Activity> {
    List<Activity> listOptions();
}
