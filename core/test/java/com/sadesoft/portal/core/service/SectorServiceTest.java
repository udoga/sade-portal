package com.sadesoft.portal.core.service;

import static com.sadesoft.portal.base.util.TestUtil.*;
import com.sadesoft.portal.core.mock.MockSectorRepo;
import com.sadesoft.portal.core.model.Sector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;

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
    public void whenHasInvalidInputOnCreate_throwsError() throws Exception{
        assertSpacedField("name");
    }

    private void assertSpacedField(String fieldName) throws Exception {
        assertInvalidInput(() -> service.create(withField(validSector, fieldName, "1")));
        assertInvalidInput(() -> service.create(withField(validSector, fieldName, "")));
        assertInvalidInput(() -> service.create(withField(validSector, fieldName, " ")));
        assertInvalidInput(() -> service.create(withField(validSector, fieldName, "a ")));
        assertDoesNotThrow(() -> service.create(withField(validSector, fieldName, "A B")));
    }

    private Sector withField(Sector sector, String fieldName, String value) throws Exception {
        Field field = sector.getClass().getDeclaredField(fieldName);
        field.set(sector, value);
        return sector;
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
    public void listsSectorOptions() {
        service.create(validSector);
        assertEquals(sectorRepo.listOptions(), service.listOptions());
    }

    @Test
    public void deletesSectorById() {
        service.create(validSector);
        Sector sector = service.delete("0");
        assertEquals(validSector.withId("0"), sector);
        assertEquals(0, service.list().size());
    }
}
