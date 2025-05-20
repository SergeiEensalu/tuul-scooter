package com.tuul.api.dto;

import lombok.Getter;

@Getter
public class UserLoginResponse {
    private final String token;

    public UserLoginResponse(String token) {
        this.token = token;
    }
}
