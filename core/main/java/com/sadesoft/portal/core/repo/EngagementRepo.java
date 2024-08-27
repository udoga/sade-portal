package com.sadesoft.portal.core.repo;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.detailed.DetailedEngagement;
import com.sadesoft.portal.core.filter.EngagementFilter;
import com.sadesoft.portal.core.model.Engagement;

import java.util.List;

public interface EngagementRepo extends Repo<Engagement> {
    List<Engagement> listBy(EngagementFilter filter);
    List<DetailedEngagement> listDetailedBy(EngagementFilter filter);
}