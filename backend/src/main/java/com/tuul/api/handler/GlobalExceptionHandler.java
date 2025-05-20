package com.tuul.api.handler;

import com.tuul.exception.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<Map<String, Object>> handleAppException(AppException e) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", e.getHttpStatus());
        body.put("message", e.getMessage());
        body.put("code", e.getCode());
        body.put("timestamp", Instant.now());
        return ResponseEntity.status(e.getHttpStatus()).body(body);
    }
}
