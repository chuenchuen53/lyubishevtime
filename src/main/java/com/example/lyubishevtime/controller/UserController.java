package com.example.lyubishevtime.controller;

import com.example.lyubishevtime.request.user.LoginRequest;
import com.example.lyubishevtime.request.user.SignupRequest;
import com.example.lyubishevtime.response.user.IsUsernameExistResponse;
import com.example.lyubishevtime.response.user.LoginResponse;
import com.example.lyubishevtime.response.user.SignUpResponse;
import com.example.lyubishevtime.service.api.UserService;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public LoginResponse login(@Validated @RequestBody LoginRequest req) {
        return userService.login(req);
    }

    @PostMapping("signup")
    public SignUpResponse signup(@Validated @RequestBody SignupRequest req) {
        return userService.signup(req);
    }

    @GetMapping("is-username-exist/{username}")
    public IsUsernameExistResponse isUsernameExist(@Pattern(regexp = "^[a-zA-Z0-9_]{6,16}$") @PathVariable String username) {
        return new IsUsernameExistResponse(userService.isUsernameExist(username));
    }

}
