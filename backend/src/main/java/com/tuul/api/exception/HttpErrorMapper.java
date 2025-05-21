package com.tuul.api.exception;

import com.tuul.api.common.dto.ApiErrorResponse;
import com.tuul.domain.exception.AppException;
import com.tuul.domain.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class HttpErrorMapper {

    public static HttpStatus mapToHttpStatus(ErrorCode code) {
        return switch (code) {
            case USER_ALREADY_EXISTS -> HttpStatus.CONFLICT;
            case INVALID_CREDENTIALS -> HttpStatus.UNAUTHORIZED;
            case VALIDATION_ERROR -> HttpStatus.BAD_REQUEST;
            case VEHICLE_NOT_FOUND -> HttpStatus.NOT_FOUND;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }

    public static ApiErrorResponse toResponse(AppException ex) {
        return ApiErrorResponse.of(ex.getCode().name(), ex.getMessage());
    }
}