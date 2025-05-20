package com.tuul.api.common.handler;

import com.tuul.api.common.dto.ApiErrorResponse;
import com.tuul.exception.AppException;
import com.tuul.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiErrorResponse> handleAppException(AppException ex) {
        HttpStatus status = mapToHttpStatus(ex.getCode());
        return ResponseEntity
                .status(status)
                .body(ApiErrorResponse.of(ex.getCode().getCode(), ex.getMessage()));
    }

    private HttpStatus mapToHttpStatus(ErrorCode code) {
        return switch (code) {
            case USER_ALREADY_EXISTS -> HttpStatus.CONFLICT;
            case INVALID_CREDENTIALS -> HttpStatus.UNAUTHORIZED;
            default -> HttpStatus.BAD_REQUEST;
        };
    }
}
