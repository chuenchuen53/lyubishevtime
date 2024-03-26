package com.example.lyubishevtime.controller;

import com.example.lyubishevtime.request.user.LoginRequest;
import com.example.lyubishevtime.request.user.RegisterRequest;
import com.example.lyubishevtime.request.user.UpdatePasswordRequest;
import com.example.lyubishevtime.response.user.CurrentUserResponse;
import com.example.lyubishevtime.response.user.IsUsernameExistResponse;
import com.example.lyubishevtime.response.user.LoginResponse;
import com.example.lyubishevtime.response.user.RegisterResponse;
import com.example.lyubishevtime.service.api.UserService;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("current-user")
    public CurrentUserResponse currentUser(@RequestAttribute("userId") Integer userId) {
        return userService.currentUser(userId);
    }

    @PostMapping("login")
    public LoginResponse login(@Validated @RequestBody LoginRequest req) {
        return userService.login(req);
    }

    @PostMapping("register")
    public RegisterResponse register(@Validated @RequestBody RegisterRequest req) {
        return userService.register(req);
    }

    @GetMapping("is-username-exist/{username}")
    public IsUsernameExistResponse isUsernameExist(@Pattern(regexp = "^[a-zA-Z0-9_]{6,16}$") @PathVariable String username) {
        return new IsUsernameExistResponse(userService.isUsernameExist(username));
    }

    @PutMapping("personal-info/profile-pic")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProfilePic(@RequestAttribute("userId") Integer userId,
                                 @Validated @RequestParam String profilePic) {
        userService.updatePersonInfo(userId, null, profilePic);
    }

    @PutMapping("personal-info/nickname")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateNickname(@RequestAttribute("userId") Integer userId,
                               @Validated @RequestParam String nickname) {
        userService.updatePersonInfo(userId, nickname, null);
    }

    @PutMapping("auth/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@Validated @RequestBody UpdatePasswordRequest req,
                               @RequestAttribute("userId") Integer userId) {
        userService.updatePassword(userId, req.getOldPassword(), req.getNewPassword());
    }
}
