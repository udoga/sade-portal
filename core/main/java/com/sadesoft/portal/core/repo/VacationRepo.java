package com.sadesoft.portal.core.repo;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.detailed.DetailedVacation;
import com.sadesoft.portal.core.model.Vacation;

import java.util.List;

public interface VacationRepo extends Repo<Vacation> {
    List<Vacation> listBy(String status);
    List<DetailedVacation> listDetailedBy(String status);
    DetailedVacation readDetailed(String id);
}
