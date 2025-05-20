package com.tuul.repository;

import com.tuul.domain.model.User;

public interface UserRepository {
    void save(User user);

    User findByEmail(String email);
}