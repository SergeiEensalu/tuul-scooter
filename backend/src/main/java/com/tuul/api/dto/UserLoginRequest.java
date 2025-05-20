package com.tuul.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserLoginRequest {
    @Email
    private String email;

    @NotBlank
    private String password;
}
