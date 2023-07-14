package com.restore.core.exception;

import com.restore.core.dto.response.Response;
import com.restore.core.dto.response.ResponseCode;
import feign.FeignException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.UUID;

@ControllerAdvice
@Slf4j
@Component
@Configuration
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RestoreSkillsException.class)
    protected ResponseEntity<Object> handleCustomException(RestoreSkillsException exception, WebRequest request) {
        log.error("Custom Exception", exception);
        return handleExceptionInternal(exception, buildResponse(exception.getErrorCode(), exception.getMessage(), request), new HttpHeaders(),
                exception.getErrorCode() == ResponseCode.INTERNAL_ERROR ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDaoException(DataIntegrityViolationException exception, WebRequest request) {
        log.error("Database Exception", exception);
        return handleExceptionInternal(exception, buildResponse(ResponseCode.DB_ERROR,
                NestedExceptionUtils.getMostSpecificCause(exception).getMessage(), request),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("Method Args Exception", exception);
        String message = exception.getBindingResult().getFieldError().getDefaultMessage();
        return handleExceptionInternal(exception, buildResponse(ResponseCode.INTERNAL_ERROR, message, request),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception exception, WebRequest request) {
        log.error("Generic Exception", exception);
        return handleExceptionInternal(exception, buildResponse(ResponseCode.INTERNAL_ERROR, exception.getMessage(), request),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> handleFeignException(FeignException exception, WebRequest request, HttpServletResponse response) {
        int errorStatusCode = exception.status();

        try {
            response.setStatus(errorStatusCode);
            JSONObject jsonObject = new JSONObject(exception.contentUTF8());
            return handleExceptionInternal(exception, buildResponse(ResponseCode.BAD_REQUEST, jsonObject.get("message") != null ? jsonObject.get("message").toString() : "no error message", request),
                    new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
        } catch (Exception e) {
            return handleExceptionInternal(exception, buildResponse(ResponseCode.SERVICE_UNAVAILABLE, exception.getMessage(), request),
                    new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);
        }

    }


    private Response buildResponse(ResponseCode code, String message, WebRequest request) {
        return Response.builder()
                .code(code)
                .message(message)
                .path(request.getContextPath())
                .requestId(UUID.randomUUID().toString())
                .errors(null)
                .version("1.0")
                .build();
    }
}
