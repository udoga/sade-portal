package com.sadesoft.portal.core.repo;

import java.util.List;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.detailed.DetailedClient;
import com.sadesoft.portal.core.model.Client;

public interface ClientRepo extends Repo<Client> {
      List<Client> listSummarized();
      List<DetailedClient> listDetailed();
      DetailedClient readDetailed(String id);
}
