package com.restore.core.dto.response;

public enum ResponseCode {

    INTERNAL_ERROR,
    USER_NOT_FOUND,
    USER_ALREADY_EXIST,
    BAD_REQUEST,
    NOT_FOUND,
    CREATED,
    SERVICE_UNAVAILABLE,
    UNAUTHORIZED,

    DB_ERROR,
    IAM_ERROR,
    AWS_ERROR,

    ENTITY,
    OK,
    UPDATED
}
