package uds.samplepanel.base;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;

public class MemoryTableTest {
    MemoryTable<MockRecord> table;

    private void assertNotFound(Executable e, int id) {
        RuntimeException exception = assertThrows(RuntimeException.class, e);
        assertEquals(table.typeName + " not found: id=" + id, exception.getMessage());
    }

    @BeforeEach
    public void setup() {
        table = new MemoryTable<>("MockRecord");
    }

    @Test
    public void keepsTypeNameAndRecords() {
        assertEquals("MockRecord", table.typeName);
        assertEquals(0, table.list().size());
    }

    @Test
    public void whenCreates_addsRecordToList() {
        table.create(new MockRecord("name"));
        assertEquals(1, table.list().size());
        assertEquals(new MockRecord(0, "name"), table.list().get(0));
    }

    @Test
    public void whenCreates_returnsAddedRecord() {
        MockRecord record0 = new MockRecord("name0");
        MockRecord record1 = new MockRecord("name1");
        assertEquals(new MockRecord(0, "name0"), table.create(record0));
        assertEquals(new MockRecord(1, "name1"), table.create(record1));
    }

    @Test
    public void whenDeletedRecordNotFound_throwsException() {
        assertNotFound(() -> table.delete(0), 0);
    }

    @Test
    public void whenReadRecordNotFound_throwsException() {
        assertNotFound(() -> table.read(0), 0);
    }

    @Test
    public void whenUpdatedRecordNotFound_throwsException() {
        assertNotFound(() -> table.update(0, new MockRecord("name0")), 0);
    }

    @Nested
    public class GivenMemoryTableWithTwoRecords {
        @BeforeEach
        public void setup() {
            table.create(new MockRecord("name0"));
            table.create(new MockRecord("name1"));
        }

        @Test
        public void whenDeletesFirst_leavesSecond() {
            MockRecord record = table.delete(0);
            assertEquals(1, table.list().size());
            assertEquals(1, table.list().get(0).id);
            assertEquals(0, record.id);
        }

        @Test
        public void whenDeletesSecond_leavesFirst() {
            MockRecord record = table.delete(1);
            assertEquals(1, table.list().size());
            assertEquals(0, table.list().get(0).id);
            assertEquals(1, record.id);
        }

        @Test
        public void whenDeletedRecordNotFound_throwsException() {
            assertNotFound(() -> table.delete(2), 2);
        }

        @Test
        public void whenDeletes_removesById() {
            table.delete(0);
            assertNotFound(() -> table.delete(0), 0);
            assertDoesNotThrow(() -> table.delete(1));
        }

        @Test
        public void whenReadsFromSequentialRecords_returnsByIndex() {
            assertEquals(table.list().get(0), table.read(0));
            assertEquals(table.list().get(1), table.read(1));
        }

        @Test
        public void whenReads_returnsById() {
            table.delete(0);
            assertEquals(table.list().get(0), table.read(1));
            assertNotFound(() -> table.read(0), 0);
        }

        @Test
        public void whenUpdates_changesName() {
            MockRecord record = table.update(0, new MockRecord("new_name"));
            assertEquals(new MockRecord(0, "new_name"), table.read(0));
            assertEquals(new MockRecord(1, "name1"), table.read(1));
            assertEquals(new MockRecord(0, "new_name"), record);
        }

        @Test
        public void whenUpdates_changesNameById() {
            table.delete(0);
            MockRecord record = table.update(1, new MockRecord("new_name"));
            assertEquals(1, table.list().size());
            assertEquals(new MockRecord(1, "new_name"), table.read(1));
            assertEquals(1, record.id);
        }
    }
}
