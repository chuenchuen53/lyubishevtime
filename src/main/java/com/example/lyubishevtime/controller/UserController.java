package com.example.lyubishevtime.controller;

import com.example.lyubishevtime.request.user.*;
import com.example.lyubishevtime.response.user.CurrentUserResponse;
import com.example.lyubishevtime.response.user.LoginResponse;
import com.example.lyubishevtime.response.user.RegisterResponse;
import com.example.lyubishevtime.service.api.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public RegisterResponse register(@Validated @RequestBody RegisterRequest req) {
        return userService.register(req);
    }

    @PostMapping("login")
    public LoginResponse login(@Validated @RequestBody LoginRequest req) {
        return userService.login(req);
    }

    @PutMapping("personal-info/profile-pic")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProfilePic(@Validated @RequestBody UpdateProfilePicRequest req,
                                 @RequestAttribute("userId") Integer userId) {
        userService.updatePersonInfo(userId, null, req.getProfilePic());
    }

    @PutMapping("personal-info/nickname")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateNickname(@Validated @RequestBody UpdateNicknameRequest req,
                               @RequestAttribute("userId") Integer userId) {
        userService.updatePersonInfo(userId, req.getNickname(), null);
    }

    @PutMapping("auth/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@Validated @RequestBody UpdatePasswordRequest req,
                               @RequestAttribute("userId") Integer userId) {
        userService.updatePassword(userId, req.getOldPassword(), req.getNewPassword());
    }

    @GetMapping("auth/current-user")
    public CurrentUserResponse currentUser(@RequestAttribute("userId") Integer userId) {
        return userService.currentUser(userId);
    }
}
