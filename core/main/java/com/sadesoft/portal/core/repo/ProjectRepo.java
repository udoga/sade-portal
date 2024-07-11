package com.sadesoft.portal.core.repo;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.detailed.DetailedProject;
import com.sadesoft.portal.core.model.Project;

import java.util.List;

public interface ProjectRepo extends Repo<Project> {
    List<DetailedProject> listDetailed();
}
