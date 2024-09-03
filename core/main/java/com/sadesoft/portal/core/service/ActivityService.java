package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.model.Activity;
import com.sadesoft.portal.core.repo.ActivityRepo;

import java.util.List;
import java.util.regex.Pattern;

public class ActivityService {
    private final ActivityRepo repo;

    public ActivityService(ActivityRepo repo) {
        this.repo = repo;
    }

    public List<Activity> list() {
        return repo.list();
    }

    public List<Activity> listSummarized() {
        return repo.listSummarized();
    }

    public Activity read(String id) {
        return repo.read(id);
    }

    public Activity create(Activity activity) {
        check(activity);
        return repo.create(activity);
    }

    public Activity update(String id, Activity activity) {
        check(activity);
        return repo.update(id, activity);
    }

    public Activity delete(String id) {
        return repo.delete(id);
    }

    private void check(Activity activity) {
        checkMissingField(activity.name, "name");
        checkFieldFormat(activity.name, "name", "^[a-zA-Z]+$");
    }

    private void checkMissingField(String value, String field) {
        if (value == null) throw new MissingFieldError(field);
    }

    private void checkFieldFormat(String value, String field, String regex) {
        if (!Pattern.matches(regex, value)) throw new InvalidInputError("Format of field '" + field + "' is invalid.");
    }
}
