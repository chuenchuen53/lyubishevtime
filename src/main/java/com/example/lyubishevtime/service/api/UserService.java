package com.example.lyubishevtime.service.api;

import com.example.lyubishevtime.request.user.LoginRequest;
import com.example.lyubishevtime.request.user.RegisterRequest;
import com.example.lyubishevtime.response.user.CurrentUserResponse;
import com.example.lyubishevtime.response.user.LoginResponse;
import com.example.lyubishevtime.response.user.RegisterResponse;

public interface UserService {
    CurrentUserResponse currentUser(int userId);

    RegisterResponse register(RegisterRequest req);

    LoginResponse login(LoginRequest req);

    void updatePersonInfo(Integer userId, String nickname, String profilePic);

    void updatePassword(Integer userId, String oldPassword, String newPassword);
}
