package com.sadesoft.portal.core.mock;

import java.util.List;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.detailed.DetailedClient;
import com.sadesoft.portal.core.query.ClientQuery;
import com.sadesoft.portal.core.model.Client;
import com.sadesoft.portal.core.model.Sector;
import com.sadesoft.portal.core.repo.ClientRepo;
import com.sadesoft.portal.core.repo.SectorRepo;

public class MockClientRepo extends MockRepo<Client> implements ClientRepo {
    private final SectorRepo sectorRepo;

    public MockClientRepo(SectorRepo sectorRepo) {
        super("Client");
        this.sectorRepo = sectorRepo;
    }

    @Override
    public List<Client> listOptions() {
        return list().stream().map(c -> new Client().withId(c.id).withName(c.name)).toList();
    }

    @Override
    public List<Client> listBy(ClientQuery query) {
        return applyOffset(records.stream().filter(e -> e.matches(query)).toList(), query.offset);
    }

    @Override
    public List<DetailedClient> listDetailedBy(ClientQuery query) {
        return listBy(query).stream().map(e -> new DetailedClient(e, getSector(e.sectorId))).toList();
    }

    @Override
    public DetailedClient readDetailed(String id) {
        Client client = read(id);
        return new DetailedClient(client, getSector(client.sectorId));
    }

    private Sector getSector(String sectorId) {
        return (sectorRepo == null)? null : sectorRepo.read(sectorId);
    }
}
