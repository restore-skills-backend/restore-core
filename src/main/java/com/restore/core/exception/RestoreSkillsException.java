package com.restore.core.exception;

import com.restore.core.dto.response.ResponseCode;
import lombok.Getter;

@Getter
public class RestoreSkillsException extends Exception {

    private ResponseCode errorCode;
    private String[] fields;
    private Exception exception;

    public RestoreSkillsException() {
        super("Failed to do operation");
        this.errorCode = ResponseCode.INTERNAL_ERROR;
        this.exception = new RuntimeException();
    }

    public RestoreSkillsException(ResponseCode code, String message, String... fields) {
        super(message);
        this.errorCode = code;
        this.fields = fields;
    }

    public RestoreSkillsException(Exception exception) {
        super(exception.getLocalizedMessage());
        this.errorCode = ResponseCode.INTERNAL_ERROR;
        this.exception = exception;
    }
}
