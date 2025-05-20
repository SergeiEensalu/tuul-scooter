package com.tuul.api.controller;

import com.tuul.api.dto.request.UserLoginRequest;
import com.tuul.api.dto.response.UserLoginResponse;
import com.tuul.api.dto.request.UserRegisterRequest;
import com.tuul.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterRequest request) {
        userService.register(request.getName(), request.getEmail(), request.getPassword());
        return ResponseEntity.ok().body("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest request) {
        String token = userService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(new UserLoginResponse(token));
    }
}

