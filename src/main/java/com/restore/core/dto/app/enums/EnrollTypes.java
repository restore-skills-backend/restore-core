package com.restore.core.dto.app.enums;

public enum EnrollTypes {
    EMAIL_ME("Email me program info"),
    ENROLL_ME("Yes, Enroll me"),
    DONT_ENROLL_ME("No, Don't enroll me");

    private final String label;
    EnrollTypes(String label){
        this.label = label;
    }
}
