package org.casbin.casibase.util;

public enum RecordOperations {
    ADD_Record("add-record"),
    DELETE_Record("delete-record"),
    UPDATE_Record("update-record"),
    GET_Records("get-records"),
    GET_Record("get-record");
    private final String operation;

    RecordOperations(String op) {
        this.operation = op;
    }

    public String getOperation() {
        return operation;
    }
}
