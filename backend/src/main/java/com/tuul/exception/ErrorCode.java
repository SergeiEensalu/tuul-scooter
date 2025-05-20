package com.tuul.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_ALREADY_EXISTS("USER_ALREADY_EXISTS", 409),
    INVALID_CREDENTIALS("INVALID_CREDENTIALS", 401);

    private final String code;
    private final int httpStatus;

    ErrorCode(String code, int httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

}
