package com.tuul.exception;

public enum ErrorCode {
    USER_ALREADY_EXISTS,
    INVALID_CREDENTIALS;

    public String getCode() {
        return name();
    }
}