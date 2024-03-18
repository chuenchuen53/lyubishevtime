package com.example.lyubishevtime.mapper;

import com.example.lyubishevtime.entity.AppUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AppUserMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO app_user(username, password, nickname, photo_url) VALUES " +
            "(#{username}, #{password}, #{nickname}, #{photoUrl})")
    int insert(AppUser appUser);

    @Select("SELECT id, username, password, nickname, photo_url FROM app_user WHERE username " +
            "= #{username}")
    AppUser selectByUsername(@Param("username") String username);

    @Select("SELECT COUNT(*) FROM app_user WHERE username = #{username}")
    int isUsernameExist(@Param("username") String username);

    int update(AppUser appUser);
}
