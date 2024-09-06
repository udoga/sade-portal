package com.sadesoft.portal.core.repo;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.detailed.DetailedProject;
import com.sadesoft.portal.core.query.ProjectQuery;
import com.sadesoft.portal.core.model.Project;

import java.util.List;

public interface ProjectRepo extends Repo<Project> {
    List<Project> listBy(ProjectQuery query);
    List<DetailedProject> listDetailedBy(ProjectQuery query);
    List<Project> listOptions();
    DetailedProject readDetailed(String id);
}
