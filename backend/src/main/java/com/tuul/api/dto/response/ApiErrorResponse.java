package com.tuul.api.dto.response;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record ApiErrorResponse(
        String status,
        String message,
        String code,
        List<String> errors,
        Instant timestamp
) {
    public static ApiErrorResponse of(String code, String message, List<String> errors) {
        return ApiErrorResponse.builder()
                .status("error")
                .message(message)
                .code(code)
                .errors(errors)
                .timestamp(Instant.now())
                .build();
    }

    public static ApiErrorResponse of(String code, String message) {
        return of(code, message, null);
    }
}