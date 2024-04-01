package com.example.lyubishevtime.dto.mapper;

import com.example.lyubishevtime.entity.AppUserEntity;
import com.example.lyubishevtime.response.user.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppUserDtoMapper {

    @Mapping(target = "token", ignore = true)
    AppUser entityToAppUser(AppUserEntity appUserEntity);
}
