package com.tuul.api.user;

import com.tuul.api.user.dto.UserLoginRequest;
import com.tuul.api.common.dto.ApiResponse;
import com.tuul.api.user.dto.UserLoginResponse;
import com.tuul.api.user.dto.UserRegisterRequest;
import com.tuul.api.user.dto.UserRegisterResponse;
import com.tuul.application.user.UserService;
import com.tuul.application.user.dto.CreateUserCommand;
import com.tuul.application.user.dto.GetUserCommand;
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
    public ResponseEntity<ApiResponse<UserRegisterResponse>> register(@Valid @RequestBody UserRegisterRequest request) {
        CreateUserCommand command = new CreateUserCommand(request.name(), request.email(), request.password());

        var createdUser = userService.register(command); // Comment by SERGEI EENSALU: Var to prevent import User from Domain/Service layer.

        return ResponseEntity.ok(
                ApiResponse.success(
                        "User registered",
                        new UserRegisterResponse(createdUser.getId(), createdUser.getEmail())
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserLoginResponse>> login(@Valid @RequestBody UserLoginRequest request) {
        GetUserCommand command = new GetUserCommand(request.email(), request.password());

        String token = userService.login(command);
        return ResponseEntity.ok(
                ApiResponse.success("Login successful", new UserLoginResponse(token))
        );
    }
}

