package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.detailed.DetailedClient;
import com.sadesoft.portal.core.query.ClientQuery;
import com.sadesoft.portal.core.model.Client;
import com.sadesoft.portal.core.repo.ClientRepo;

import java.util.List;
import java.util.regex.Pattern;

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

    public List<Client> listOptions() {
        return repo.listOptions();
    }

    public Client read(String id) {
        return repo.read(id);
    }

    public DetailedClient readDetailed(String id) {
        return repo.readDetailed(id);
    }

    public Client create(Client client) {
        check(client);
        return repo.create(client);
    }

    public Client update(String id, Client client) {
        check(client);
        return repo.update(id, client);
    }

    public Client delete(String id) {
        return repo.delete(id);
    }

    private void check(Client client) {
        checkMissingFields(client);
        checkFieldFormats(client);
    }

    private void checkMissingFields(Client client) {
        checkMissingField(client.sectorId, "sectorId");
        checkMissingField(client.name, "name");
    }

    private void checkFieldFormats(Client client) {
        checkFieldFormat(client.sectorId, "sectorId", "^[0-9]+$");
        checkFieldFormat(client.name, "name", "^[a-zA-Z]+( [a-zA-Z]+)*$");
    }

    private void checkMissingField(String value, String field) {
        if (value == null) throw new MissingFieldError(field);
    }

    private void checkFieldFormat(String value, String field, String regex) {
        if (!Pattern.matches(regex, value)) throw new InvalidInputError("Format of field '" + field + "' is invalid.");
    }
}
