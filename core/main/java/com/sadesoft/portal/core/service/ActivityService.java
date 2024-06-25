package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.model.Activity;
import com.sadesoft.portal.core.repo.ActivityRepo;

import java.util.List;

public class ActivityService {
    private final ActivityRepo repo;

    public ActivityService(ActivityRepo repo) {
        this.repo = repo;
    }

    public List<Activity> list() {
        return repo.list();
    }

    public Activity read(String id) {
        return repo.read(id);
    }

    public Activity create(Activity activity) {
        return repo.create(activity);
    }

    public Activity update(String id, Activity activity) {
        return repo.update(id, activity);
    }

    public Activity delete(String id) {
        return repo.delete(id);
    }
}
