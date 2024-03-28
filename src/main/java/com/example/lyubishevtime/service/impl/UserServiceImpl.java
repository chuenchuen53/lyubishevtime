package com.example.lyubishevtime.service.impl;

import com.example.lyubishevtime.auth.JwtHelper;
import com.example.lyubishevtime.entity.AppUserEntity;
import com.example.lyubishevtime.entity.TimeEventTagOrderEntity;
import com.example.lyubishevtime.exception.ApiException;
import com.example.lyubishevtime.mapper.AppUserMapper;
import com.example.lyubishevtime.mapper.TimeEventTagOrderMapper;
import com.example.lyubishevtime.request.user.LoginRequest;
import com.example.lyubishevtime.request.user.RegisterRequest;
import com.example.lyubishevtime.response.user.CurrentUserResponse;
import com.example.lyubishevtime.response.user.LoginResponse;
import com.example.lyubishevtime.response.user.RegisterResponse;
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
    public CurrentUserResponse currentUser(int userId) {
        AppUserEntity appUser = appUserMapper.selectById(userId);
        String token = jwtHelper.createToken(Long.valueOf(appUser.getId()));
        return CurrentUserResponse.builder()
                .id(appUser.getId())
                .username(appUser.getUsername())
                .nickname(appUser.getNickname())
                .profilePic(appUser.getProfilePic())
                .token(token)
                .build();
    }

    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest req) {
        if (isUsernameExist(req.getUsername())) {
            throw new ApiException(HttpStatus.CONFLICT, "Username already exists");
        }

        AppUserEntity appUser = AppUserEntity.builder()
                .username(req.getUsername())
                .password(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt()))
                .nickname(req.getNickname())
                .build();
        appUserMapper.insert(appUser);

        TimeEventTagOrderEntity timeEventTagOrderEntity = TimeEventTagOrderEntity.builder()
                .userId(appUser.getId())
                .tagIds(Collections.emptyList())
                .build();
        timeEventTagOrderMapper.insert(timeEventTagOrderEntity);

        return RegisterResponse.builder()
                .username(req.getUsername())
                .nickname(req.getNickname())
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest req) {
        AppUserEntity appUserEntity = appUserMapper.selectByUsername(req.getUsername());
        if (appUserEntity == null || !BCrypt.checkpw(req.getPassword(), appUserEntity.getPassword())) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Incorrect username or password");
        }
        String token = jwtHelper.createToken(Long.valueOf(appUserEntity.getId()));
        return LoginResponse.builder()
                .id(appUserEntity.getId())
                .username(appUserEntity.getUsername())
                .nickname(appUserEntity.getNickname())
                .profilePic(appUserEntity.getProfilePic())
                .token(token)
                .build();
    }

    @Override
    public boolean isUsernameExist(String username) {
        return appUserMapper.isUsernameExist(username);
    }

    @Override
    public boolean updatePersonInfo(Integer userId, String nickname, String profilePic) {
        AppUserEntity appUserEntity = AppUserEntity.builder()
                .id(userId)
                .nickname(nickname)
                .profilePic(profilePic)
                .build();
        int updated = appUserMapper.update(appUserEntity);
        return updated == 1;
    }

    @Override
    public boolean updatePassword(Integer userId, String oldPassword, String newPassword) {
        AppUserEntity appUserEntity = appUserMapper.selectById(userId);
        if (!BCrypt.checkpw(oldPassword, appUserEntity.getPassword())) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Incorrect old password");
        }
        AppUserEntity updatedAppUser = AppUserEntity.builder()
                .id(userId)
                .password(BCrypt.hashpw(newPassword, BCrypt.gensalt()))
                .build();
        int updated = appUserMapper.update(updatedAppUser);
        return updated == 1;
    }
}
