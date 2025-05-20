package com.tuul.api.exception;

import com.tuul.api.common.dto.ApiErrorResponse;
import com.tuul.api.common.dto.ValidationError;
import com.tuul.domain.exception.AppException;
import com.tuul.domain.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiErrorResponse> handleAppException(AppException ex) {
        return ResponseEntity
                .status(HttpErrorMapper.mapToHttpStatus(ex.getCode()))
                .body(HttpErrorMapper.toResponse(ex));
    }

    private HttpStatus mapToHttpStatus(ErrorCode code) {
        return switch (code) {
            case USER_ALREADY_EXISTS -> HttpStatus.CONFLICT;
            case INVALID_CREDENTIALS -> HttpStatus.UNAUTHORIZED;
            default -> HttpStatus.BAD_REQUEST;
        };
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<ValidationError> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ValidationError(
                        fieldError.getField(),
                        fieldError.getDefaultMessage()
                ))
                .toList();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiErrorResponse.of("VALIDATION_ERROR", "Validation failed", errors));
    }
}
