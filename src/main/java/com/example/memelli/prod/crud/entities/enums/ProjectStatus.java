package com.example.memelli.prod.crud.entities.enums;

public enum ProjectStatus {
    PENDING(1),
    ONGOING(2),
    FINISHED(3);

    private int code;

    private ProjectStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ProjectStatus valueofStatus(int code) {
        for (ProjectStatus t : ProjectStatus.values()) {
            if (t.getCode() == code) {
                return t;
            }
        }
        throw new IllegalArgumentException("Invalid TaskStatus code");
    }
}