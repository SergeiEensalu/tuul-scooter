package com.tuul.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
    private final String code;
    private final int httpStatus;

    public AppException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
        this.httpStatus = errorCode.getHttpStatus();
    }

}
