package com.tuul.api.dto.response;

import lombok.Builder;

import java.time.Instant;

@Builder
public record ApiResponse<T>(
        String status,
        String message,
        T data,
        Instant timestamp
) {
    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .status("success")
                .message(message)
                .data(data)
                .timestamp(Instant.now())
                .build();
    }

    public static ApiResponse<Void> success(String message) {
        return success(message, null);
    }
}
