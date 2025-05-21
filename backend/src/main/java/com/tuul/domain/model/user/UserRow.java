package com.tuul.domain.model.user;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserRow {
    private String email;
    private String passwordHash;
    private String name;

    @Builder.Default
    private Date createdAt = new Date();

    @Builder.Default
    private Date updatedAt = new Date();

}