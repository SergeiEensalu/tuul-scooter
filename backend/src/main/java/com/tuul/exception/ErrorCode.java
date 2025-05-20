package com.tuul.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_ALREADY_EXISTS("USER_ALREADY_EXISTS"),
    INVALID_CREDENTIALS("INVALID_CREDENTIALS");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }
}

