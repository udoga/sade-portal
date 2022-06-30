package com.sadesoft.portal.base.repo;

import java.util.Objects;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;
import com.sadesoft.portal.base.error.RecordNotFound;
import com.sadesoft.portal.base.util.TestUtil;

public class ListRepoTest {
    ListRepo<MockRecord> repo;

    private void assertNotFound(Executable e, int id) {
        TestUtil.assertException(RecordNotFound.class, "Record not found in " + repo.tableName + ": id=" + id, e);
    }

    @BeforeEach
    public void setup() {
        repo = new ListRepo<>("MockRecords");
    }

    @Test
    public void keepsTypeNameAndRecords() {
        assertEquals("MockRecords", repo.tableName);
        assertEquals(0, repo.list().size());
    }

    @Test
    public void whenCreates_addsRecordToList() {
        repo.create(new MockRecord("name"));
        assertEquals(1, repo.list().size());
        assertEquals(new MockRecord(0, "name"), repo.list().get(0));
    }

    @Test
    public void whenCreates_returnsAddedRecord() {
        MockRecord record0 = new MockRecord("name0");
        MockRecord record1 = new MockRecord("name1");
        assertEquals(new MockRecord(0, "name0"), repo.create(record0));
        assertEquals(new MockRecord(1, "name1"), repo.create(record1));
    }

    @Test
    public void whenDeletedRecordNotFound_throwsException() {
        assertNotFound(() -> repo.delete(0), 0);
    }

    @Test
    public void whenReadRecordNotFound_throwsException() {
        assertNotFound(() -> repo.read(0), 0);
    }

    @Test
    public void whenUpdatedRecordNotFound_throwsException() {
        assertNotFound(() -> repo.update(0, new MockRecord("name0")), 0);
    }

    @Nested
    public class GivenListRepoWithTwoRecords {
        @BeforeEach
        public void setup() {
            repo.create(new MockRecord("name0"));
            repo.create(new MockRecord("name1"));
        }

        @Test
        public void whenDeletesFirst_leavesSecond() {
            MockRecord record = repo.delete(0);
            assertEquals(1, repo.list().size());
            assertEquals(1, repo.list().get(0).id);
            assertEquals(0, record.id);
        }

        @Test
        public void whenDeletesSecond_leavesFirst() {
            MockRecord record = repo.delete(1);
            assertEquals(1, repo.list().size());
            assertEquals(0, repo.list().get(0).id);
            assertEquals(1, record.id);
        }

        @Test
        public void whenDeletedRecordNotFound_throwsException() {
            assertNotFound(() -> repo.delete(2), 2);
        }

        @Test
        public void whenDeletes_removesById() {
            repo.delete(0);
            assertNotFound(() -> repo.delete(0), 0);
            assertDoesNotThrow(() -> repo.delete(1));
        }

        @Test
        public void whenReadsFromSequentialRecords_returnsByIndex() {
            assertEquals(repo.list().get(0), repo.read(0));
            assertEquals(repo.list().get(1), repo.read(1));
        }

        @Test
        public void whenReads_returnsById() {
            repo.delete(0);
            assertEquals(repo.list().get(0), repo.read(1));
            assertNotFound(() -> repo.read(0), 0);
        }

        @Test
        public void whenUpdates_changesName() {
            MockRecord record = repo.update(0, new MockRecord("new_name"));
            assertEquals(new MockRecord(0, "new_name"), repo.read(0));
            assertEquals(new MockRecord(1, "name1"), repo.read(1));
            assertEquals(new MockRecord(0, "new_name"), record);
        }

        @Test
        public void whenUpdates_changesNameById() {
            repo.delete(0);
            MockRecord record = repo.update(1, new MockRecord("new_name"));
            assertEquals(1, repo.list().size());
            assertEquals(new MockRecord(1, "new_name"), repo.read(1));
            assertEquals(1, record.id);
        }

        @Test
        public void whenDeletesFirstAndCreates_assignsUniqueId() {
            repo.delete(0);
            repo.create(new MockRecord("name"));
            assertEquals(2, repo.list().size());
            assertEquals(1, repo.list().get(0).id);
            assertEquals(2, repo.list().get(1).id);
        }
    }

    private static class MockRecord implements Record {
        public Integer id;
        public final String name;

        MockRecord(String name) {
            this(null, name);
        }

        MockRecord(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof MockRecord && this.equals((MockRecord) o);
        }

        public boolean equals(MockRecord record) {
            return Objects.equals(id, record.id) && name.equals(record.name);
        }

        @Override
        public Integer getId() {
            return id;
        }

        @Override
        public void setId(Integer id) {
            this.id = id;
        }
    }
}
