package com.tuul.application.user.dto;

public record GetUserCommand(
        String email,
        String rawPassword
) {
}
