package com.tuul.repository;

import com.tuul.domain.model.user.User;
import com.tuul.domain.model.user.UserRow;

public interface UserRepository {
    User save(UserRow userRow);

    User findByEmail(String email);
}