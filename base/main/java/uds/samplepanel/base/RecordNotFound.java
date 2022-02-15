package uds.samplepanel.base;

public class RecordNotFound extends RuntimeException {
    public RecordNotFound(String typeName, int id) {
        super(typeName + " not found: id=" + id);
    }
}
