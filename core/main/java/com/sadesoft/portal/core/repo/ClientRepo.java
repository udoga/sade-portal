package com.sadesoft.portal.core.repo;

import java.util.List;

import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.detailed.DetailedClient;
import com.sadesoft.portal.core.filter.ClientFilter;
import com.sadesoft.portal.core.model.Client;

public interface ClientRepo extends Repo<Client> {
      List<Client> listSummarized();
      List<Client> listBy(ClientFilter filter);
      List<DetailedClient> listDetailedBy(ClientFilter filter);
      DetailedClient readDetailed(String id);
}
