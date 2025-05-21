package com.tuul.application.user.dto;

public record CreateUserCommand(
        String name,
        String email,
        String password
) {}