package com.sadesoft.portal.core.repo;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.detailed.DetailedEngagement;
import com.sadesoft.portal.core.query.EngagementQuery;
import com.sadesoft.portal.core.model.Engagement;

import java.util.List;

public interface EngagementRepo extends Repo<Engagement> {
    List<Engagement> listBy(EngagementQuery query);
    List<DetailedEngagement> listDetailedBy(EngagementQuery query);
}