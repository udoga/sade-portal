package com.sadesoft.portal.base.error;

public class RecordNotFoundError extends InvalidInputError {
    public RecordNotFoundError(String tableName, int id) {
        super("Record not found in " + tableName + ": id=" + id);
    }
}
