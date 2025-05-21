package com.tuul.domain.model.user;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class User {
    private String id;

    private String email;
    private String passwordHash;
    private String name;

    private Date createdAt;
    private Date updatedAt;

    public static User from(String id, UserRow row) {
        return User.builder()
                .id(id)
                .email(row.getEmail())
                .name(row.getName())
                .passwordHash(row.getPasswordHash())
                .createdAt(row.getCreatedAt())
                .updatedAt(row.getUpdatedAt())
                .build();
    }
}
