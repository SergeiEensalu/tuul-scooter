package com.tuul.repository;

import com.tuul.domain.model.user.User;
import com.tuul.domain.model.user.UserRow;

import java.util.Optional;

public interface UserRepository {
    User save(UserRow userRow);

    Optional<User> findByEmail(String email);
}