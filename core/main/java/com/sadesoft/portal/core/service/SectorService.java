package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.model.Sector;
import com.sadesoft.portal.core.repo.SectorRepo;

import java.util.List;

public class SectorService {
    private final SectorRepo repo;

    public SectorService(SectorRepo repo) {
        this.repo = repo;
    }

    public List<Sector> list() {
        return repo.list();
    }

    public List<Sector> listSummarized() {
        return repo.listSummarized();
    }

    public Sector read(String id) {
        return repo.read(id);
    }

    public Sector create(Sector sector) {
        return repo.create(sector);
    }

    public Sector delete(String id) {
        return repo.delete(id);
    }
}
