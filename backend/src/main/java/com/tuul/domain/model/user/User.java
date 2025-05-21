package com.tuul.domain.model.user;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User extends UserRow {

    private String id;

    @Builder
    public User(String id, String email, String passwordHash, String name) {
        super(email, passwordHash, name);
        this.id = id;
    }
}
