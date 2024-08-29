package com.sadesoft.portal.core.repo;

import java.util.List;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.detailed.DetailedGrant;
import com.sadesoft.portal.core.query.GrantQuery;
import com.sadesoft.portal.core.model.Grant;

public interface GrantRepo extends Repo<Grant> {
    List<Grant> listBy(GrantQuery query);
    List<DetailedGrant> listDetailedBy(GrantQuery query);
}
