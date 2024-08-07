package com.sadesoft.portal.core.detailed;

import com.sadesoft.portal.core.model.Client;
import com.sadesoft.portal.core.model.Sector;

public class DetailedClient extends Client {
    public Sector sector;

    public DetailedClient(Client c) {
        super(c);
    }

    public DetailedClient(Client c, Sector s) {
        super(c);
        this.sector = s;
    }

    public DetailedClient copy() {
        try {
            return (DetailedClient) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public DetailedClient withSector(Sector sector) {
        DetailedClient detailedClient = copy();
        detailedClient.sector = sector;
        return detailedClient;
    }
}
