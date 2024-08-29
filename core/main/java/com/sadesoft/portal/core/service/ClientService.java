package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.detailed.DetailedClient;
import com.sadesoft.portal.core.query.ClientQuery;
import com.sadesoft.portal.core.model.Client;
import com.sadesoft.portal.core.repo.ClientRepo;

import java.util.List;

public class ClientService {
    private final ClientRepo repo;

    public ClientService(ClientRepo repo) {
        this.repo = repo;
    }

    public List<Client> listBy(ClientQuery query) {
        return repo.listBy(query);
    }

    public List<DetailedClient> listDetailedBy(ClientQuery query) {
        return repo.listDetailedBy(query);
    }

    public List<Client> listSummarized() {
        return repo.listSummarized();
    }

    public Client read(String id) {
        return repo.read(id);
    }

    public DetailedClient readDetailed(String id) {
        return repo.readDetailed(id);
    }

    public Client create(Client client) {
        return repo.create(client);
    }

    public Client update(String id, Client client) {
        return repo.update(id, client);
    }

    public Client delete(String id) {
        return repo.delete(id);
    }
}
