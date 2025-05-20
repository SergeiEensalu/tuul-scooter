package com.tuul.application.service;

import com.tuul.api.dto.UserRegisterRequest;
import com.tuul.infrastructure.firestore.UserDao;
import com.tuul.domain.model.User;
import com.tuul.infrastructure.security.JwtProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserDao userDao;
    private final JwtProvider jwtProvider;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserDao userDao, JwtProvider jwtProvider) {
        this.userDao = userDao;
        this.jwtProvider = jwtProvider;
    }

    public void register(UserRegisterRequest request) throws Exception {
        if (userDao.findByEmail(request.getEmail()) != null) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(encoder.encode(request.getPassword()));

        userDao.save(user);
    }

    public String login(String email, String rawPassword) throws Exception {
        User existingUser = userDao.findByEmail(email);
        if (existingUser == null) {
            throw new RuntimeException("Invalid credentials");
        }

        if (!encoder.matches(rawPassword, existingUser.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtProvider.generateToken(existingUser.getId(), existingUser.getEmail());
    }
}
