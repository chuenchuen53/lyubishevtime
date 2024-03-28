package com.example.lyubishevtime.mapper;

import com.example.lyubishevtime.entity.TimeEventTagEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TimeEventTagMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO time_event_tag (user_id, name, color) VALUES (#{userId}, #{name}, " +
            "#{color}::time_event_tag_color)")
    Integer addTimeEventTag(TimeEventTagEntity timeEventTagEntity);

    @Select("SELECT id, name, color FROM time_event_tag WHERE user_id = #{userId}")
    List<TimeEventTagEntity> listTimeEventTag(@Param("userId") Integer userId);

    Integer update(TimeEventTagEntity timeEventTagEntity);

    @Delete("DELETE FROM time_event_tag WHERE id = #{id} AND user_id = #{userId}")
    Integer delete(@Param("id") Integer id, @Param("userId") Integer userId);

    @Select("SELECT EXISTS (SELECT 1 FROM time_event_tag WHERE id = #{id} AND user_id = #{userId})")
    Boolean isTagExist(TimeEventTagEntity timeEventTagEntity);
}
