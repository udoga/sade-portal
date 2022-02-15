package uds.samplepanel.base;

import java.util.Objects;

public class MockRecord extends Record {
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
}
