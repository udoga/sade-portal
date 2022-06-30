package com.sadesoft.portal.base.error;

public class RecordNotFound extends RuntimeException {
    public RecordNotFound(String tableName, int id) {
        super("Record not found in " + tableName + ": id=" + id);
    }
}
