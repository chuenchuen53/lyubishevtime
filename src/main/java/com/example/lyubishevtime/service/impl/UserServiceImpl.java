package com.example.lyubishevtime.service.impl;

import com.example.lyubishevtime.auth.JwtHelper;
import com.example.lyubishevtime.entity.AppUser;
import com.example.lyubishevtime.entity.TimeEventTagOrder;
import com.example.lyubishevtime.exception.ApiException;
import com.example.lyubishevtime.mapper.AppUserMapper;
import com.example.lyubishevtime.mapper.TimeEventTagOrderMapper;
import com.example.lyubishevtime.request.user.LoginRequest;
import com.example.lyubishevtime.request.user.SignupRequest;
import com.example.lyubishevtime.response.user.LoginResponse;
import com.example.lyubishevtime.response.user.SignUpResponse;
import com.example.lyubishevtime.service.api.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    private final AppUserMapper appUserMapper;
    private final TimeEventTagOrderMapper timeEventTagOrderMapper;
    private final JwtHelper jwtHelper;

    public UserServiceImpl(AppUserMapper appUserMapper, TimeEventTagOrderMapper timeEventTagOrderMapper,
                           JwtHelper jwtHelper) {
        this.appUserMapper = appUserMapper;
        this.timeEventTagOrderMapper = timeEventTagOrderMapper;
        this.jwtHelper = jwtHelper;
    }

    @Override
    @Transactional
    public SignUpResponse signup(SignupRequest req) {
        if (isUsernameExist(req.getUsername())) {
            throw new ApiException(HttpStatus.CONFLICT, "Username already exists");
        }

        AppUser appUser = AppUser.builder()
                .username(req.getUsername())
                .password(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt()))
                .nickname(req.getNickname())
                .build();
        appUserMapper.insert(appUser);

        TimeEventTagOrder timeEventTagOrder = TimeEventTagOrder.builder()
                .appUser(appUser)
                .tagIds(Collections.emptyList())
                .build();
        timeEventTagOrderMapper.insert(timeEventTagOrder);

        return SignUpResponse.builder()
                .username(req.getUsername())
                .nickname(req.getNickname())
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest req) {
        AppUser appUser = appUserMapper.selectByUsername(req.getUsername());
        if (appUser == null || !BCrypt.checkpw(req.getPassword(), appUser.getPassword())) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Incorrect username or password");
        }
        String token = jwtHelper.createToken(Long.valueOf(appUser.getId()));
        return LoginResponse.builder()
                .username(appUser.getUsername())
                .nickname(appUser.getNickname())
                .photoUrl(appUser.getPhotoUrl())
                .token(token)
                .build();
    }

    @Override
    public boolean isUsernameExist(String username) {
        return appUserMapper.isUsernameExist(username) == 1;
    }
}
