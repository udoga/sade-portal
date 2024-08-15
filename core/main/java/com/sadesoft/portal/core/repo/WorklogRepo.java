package com.sadesoft.portal.core.repo;

import java.util.List;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.detailed.DetailedWorklog;
import com.sadesoft.portal.core.filter.WorklogFilter;
import com.sadesoft.portal.core.model.Worklog;
import com.sadesoft.portal.core.order.WorklogOrder;

public interface WorklogRepo extends Repo<Worklog> {
      List<Worklog> listBy(WorklogFilter filter, WorklogOrder order);
      List<DetailedWorklog> listDetailedBy(WorklogFilter filter, WorklogOrder order);
      DetailedWorklog readDetailed(String id);
}
