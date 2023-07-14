package com.restore.core.dto.app.enums;

public enum UserType {

    FACILITY_ADMIN("Facility Admin"),
    SPECIALIST("SPECIALIST"),
    PRIMARY_CARE_ADMIN("Primary Care Provider"),
    PHYSICIAN_ASSISTANT("Physician Assistant"),
    PHYSICIAN("PHYSICIAN");
    private final String name;

    UserType(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}