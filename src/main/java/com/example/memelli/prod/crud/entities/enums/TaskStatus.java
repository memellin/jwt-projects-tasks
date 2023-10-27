package com.example.memelli.prod.crud.entities.enums;

public enum TaskStatus {
    PENDING(1),
    ONGOING(2),
    FINISHED(3);

    private int code;

    private TaskStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static TaskStatus valueofStatus(int code) {
        for (TaskStatus t : TaskStatus.values()) {
            if (t.getCode() == code) {
                return t;
            }
        }
        throw new IllegalArgumentException("Invalid TaskStatus code");
    }
}