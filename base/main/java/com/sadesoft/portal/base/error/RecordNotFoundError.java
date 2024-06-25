package com.sadesoft.portal.base.error;

public class RecordNotFoundError extends ClientError {
    public RecordNotFoundError(String tableName, String message) {
        super("Record not found in " + tableName + ": " + message);
    }
}
