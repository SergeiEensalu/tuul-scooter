package com.tuul.application.user;

import com.tuul.application.user.dto.CreateUserCommand;
import com.tuul.application.user.dto.GetUserCommand;
import com.tuul.domain.exception.ErrorCode;
import com.tuul.domain.model.user.User;
import com.tuul.domain.exception.AppException;
import com.tuul.domain.model.user.UserRow;
import com.tuul.repository.UserRepository;
import com.tuul.security.JwtProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    public User register(CreateUserCommand command) {
        if (userRepository.findByEmail(command.email()).isPresent()) {
            throw new AppException(ErrorCode.USER_ALREADY_EXISTS, "User already exists");
        }

        String password = encoder.encode(command.password()); // Comment by SERGEI EENSALU: next layer should know nothing about logic, and only store data. So lets encode password in service tier.

        UserRow userRow = UserRow.builder()
                .email(command.email())
                .passwordHash(password)
                .name(command.name())
                .build();

        return userRepository.save(userRow);
    }

    public String login(GetUserCommand command) {
        User existingUser = userRepository.findByEmail(command.email())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_CREDENTIALS, "Invalid credentials"));

        if (!encoder.matches(command.rawPassword(), existingUser.getPasswordHash())) {
            throw new AppException(ErrorCode.INVALID_CREDENTIALS, "Invalid credentials");
        }

        return jwtProvider.generateToken(existingUser.getId(), existingUser.getEmail());
    }
}
