package com.tuul.repository;

import com.tuul.domain.model.user.User;

public interface UserRepository {
    User save(String name, String email, String password);

    User findByEmail(String email);
}