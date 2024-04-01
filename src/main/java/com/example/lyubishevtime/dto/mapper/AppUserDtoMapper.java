package com.example.lyubishevtime.dto.mapper;

import com.example.lyubishevtime.entity.AppUserEntity;
import com.example.lyubishevtime.response.user.CurrentUserResponse;
import com.example.lyubishevtime.response.user.LoginResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppUserDtoMapper {

    @Mapping(target = "token", ignore = true)
    CurrentUserResponse entityToCurrentUserResponse(AppUserEntity appUserEntity);

    @Mapping(target = "token", ignore = true)
    LoginResponse entityToLoginResponse(AppUserEntity appUserEntity);
}
