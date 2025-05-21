package com.tuul.application;

import com.tuul.domain.exception.ErrorCode;
import com.tuul.domain.model.User;
import com.tuul.domain.exception.AppException;
import com.tuul.repository.UserRepository;
import com.tuul.security.JwtProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    public void register(String name, String email, String password) {
        if (userRepository.findByEmail(email) != null) {
            throw new AppException(ErrorCode.USER_ALREADY_EXISTS, "User already exists");
        }

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(name);
        user.setEmail(email);
        user.setPasswordHash(encoder.encode(password));

        userRepository.save(user);
    }

    public String login(String email, String rawPassword) {
        User existingUser = userRepository.findByEmail(email);
        if (existingUser == null || !encoder.matches(rawPassword, existingUser.getPasswordHash())) {
            throw new AppException(ErrorCode.INVALID_CREDENTIALS, "Invalid credentials");
        }

        return jwtProvider.generateToken(existingUser.getId(), existingUser.getEmail());
    }
}
