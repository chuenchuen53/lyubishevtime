package com.example.lyubishevtime.mapper;

import com.example.lyubishevtime.entity.AppUserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AppUserMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO app_user(username, password, nickname, profile_pic) VALUES " +
            "(#{username}, #{password}, #{nickname}, #{profilePic})")
    Integer insert(AppUserEntity appUserEntity);

    @Select("SELECT id, username, password, nickname, profile_pic FROM app_user WHERE username " +
            "= #{username}")
    AppUserEntity selectByUsername(@Param("username") String username);

    @Select("SELECT id, username, password, nickname, profile_pic FROM app_user WHERE id " +
            "= #{id}")
    AppUserEntity selectById(@Param("id") int id);

    @Select("SELECT EXISTS (SELECT 1 FROM app_user WHERE username = #{username})")
    Boolean isUsernameExist(@Param("username") String username);

    Integer update(AppUserEntity appUserEntity);
}
