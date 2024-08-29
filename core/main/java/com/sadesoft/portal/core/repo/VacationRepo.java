package com.sadesoft.portal.core.repo;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.detailed.DetailedVacation;
import com.sadesoft.portal.core.query.VacationQuery;
import com.sadesoft.portal.core.model.Vacation;

import java.util.List;

public interface VacationRepo extends Repo<Vacation> {
    List<Vacation> listBy(VacationQuery query);
    List<DetailedVacation> listDetailedBy(VacationQuery query);
    DetailedVacation readDetailed(String id);
}
