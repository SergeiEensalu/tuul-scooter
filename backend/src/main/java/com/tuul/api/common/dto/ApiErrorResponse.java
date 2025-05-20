package com.tuul.api.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
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