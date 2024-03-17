package com.example.lyubishevtime.service.api;

import com.example.lyubishevtime.request.user.LoginRequest;
import com.example.lyubishevtime.request.user.SignupRequest;
import com.example.lyubishevtime.response.user.LoginResponse;
import com.example.lyubishevtime.response.user.SignUpResponse;

public interface UserService {
    SignUpResponse signup(SignupRequest req);

    LoginResponse login(LoginRequest req);

    boolean isUsernameExist(String username);
}
