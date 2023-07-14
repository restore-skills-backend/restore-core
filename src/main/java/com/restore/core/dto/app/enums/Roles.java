package com.restore.core.dto.app.enums;

public enum Roles {
    SUPER_ADMIN("ROLE_SUPER_ADMIN"),
    ADMIN_USER("ROLE_ADMIN_USER"),
    PROVIDER_ADMIN("ROLE_PROVIDER_ADMIN"),
    PROVIDER("ROLE_PROVIDER"),
    ADMIN("ROLE_ADMIN"),
    STAFF("ROLE_STAFF"),
    NURSE("ROLE_NURSE"),
    PATIENT("ROLE_PATIENT"),
    ANONYMOUS("ROLE_ANONYMOUS");

    private final String name;

    Roles(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }


}
