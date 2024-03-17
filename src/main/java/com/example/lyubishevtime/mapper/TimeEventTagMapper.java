package com.example.lyubishevtime.mapper;

import com.example.lyubishevtime.entity.TimeEventTag;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TimeEventTagMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO time_event_tag (user_id, name, color) VALUES (#{user.id}, #{name}, " +
            "#{color}::time_event_tag_color)")
    int addTimeEventTag(TimeEventTag timeEventTag);

    @Select("SELECT id, name, color FROM time_event_tag WHERE user_id = #{userId}")
    List<TimeEventTag> listTimeEventTag(@Param("userId") Integer userId);

    int update(TimeEventTag timeEventTag);

    @Delete("DELETE FROM time_event_tag WHERE id = #{id} AND user_id = #{userId}")
    int delete(@Param("id") Integer id, @Param("userId") Integer userId);
}
