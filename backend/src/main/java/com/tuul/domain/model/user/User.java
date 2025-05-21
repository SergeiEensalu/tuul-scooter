package com.tuul.domain.model.user;

import com.google.cloud.firestore.annotation.Exclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private String id;

    private String email;
    private String passwordHash;
    private String name;

    public static User from(String id, UserRow row) {
        return User.builder()
                .id(id)
                .email(row.getEmail())
                .name(row.getName())
                .passwordHash(row.getPasswordHash())
                .build();
    }
}
