package com.sadesoft.portal.core.repo;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.model.Client;

import java.util.List;

public interface ClientRepo extends Repo<Client> {
    List<Client> listSummarized();
}
