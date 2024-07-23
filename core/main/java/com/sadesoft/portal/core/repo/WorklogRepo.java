package com.sadesoft.portal.core.repo;

import java.util.List;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.detailed.DetailedWorklog;
import com.sadesoft.portal.core.model.Worklog;

public interface WorklogRepo extends Repo<Worklog> {
      public List<DetailedWorklog> listDetailed();
      DetailedWorklog readDetailed(String id);
}
