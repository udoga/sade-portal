package com.sadesoft.portal.core.repo;

import java.util.List;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.model.Position;

public interface PositionRepo extends Repo<Position> {
        List<Position> listOptions();

}
