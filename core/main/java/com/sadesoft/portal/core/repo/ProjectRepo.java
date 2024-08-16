package com.sadesoft.portal.core.repo;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.detailed.DetailedProject;
import com.sadesoft.portal.core.filter.ProjectFilter;
import com.sadesoft.portal.core.model.Project;

import java.util.List;

public interface ProjectRepo extends Repo<Project> {
    List<Project> listBy(ProjectFilter filter);
    List<DetailedProject> listDetailedBy(ProjectFilter filter);
    DetailedProject readDetailed(String id);
    List<Project> listSummarized();
}
