package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.mock.MockSectorRepo;
import com.sadesoft.portal.core.model.Sector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SectorServiceTest {
    private MockSectorRepo sectorRepo;
    private SectorService service;
    private Sector validSector;

    @BeforeEach
    public void setup() {
        sectorRepo = new MockSectorRepo();
        service = new SectorService(sectorRepo);
        validSector = new Sector(null, "Example Sector");
    }

    @Test
    public void createsSector() {
        Sector sector = service.create(validSector);
        assertEquals(1, sectorRepo.list().size());
        assertEquals(sector, sectorRepo.read("0"));
    }

    @Test
    public void readSector() {
        service.create(validSector);
        assertEquals(validSector.withId("0"), service.read("0"));
    }

    @Test
    public void listsSectors() {
        service.create(validSector);
        assertEquals(sectorRepo.list(), service.list());
    }

    @Test
    public void listSummarizedSectors() {
        assertEquals(1, service.listSummarized().size());
    }

    @Test
    public void deletesSectorById() {
        service.create(validSector);
        Sector sector = service.delete("0");
        assertEquals(validSector.withId("0"), sector);
        assertEquals(0, service.list().size());
    }
}
