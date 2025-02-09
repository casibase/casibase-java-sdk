package org.casbin.casibase.util;

public enum TaskOperations {
    GET_Tasks("get-tasks"),
    GET_Task("get-task"),
    ADD_Task("add-task"),
    DELETE_Task("delete-task"),
    UPDATE_Task("update-task");
    private final String operation;

    TaskOperations(String op) {
        this.operation = op;
    }

    public String getOperation() {
        return operation;
    }
}
