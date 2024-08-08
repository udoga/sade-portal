package com.sadesoft.portal.core.repo;

import java.util.List;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.model.Sector;

public interface SectorRepo extends Repo<Sector> {
    List<Sector> listSummarized();
}
