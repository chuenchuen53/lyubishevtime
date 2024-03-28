package com.example.lyubishevtime.mapper;

import com.example.lyubishevtime.entity.TimeEventEntity;
import com.example.lyubishevtime.entity.TimeEventTag;
import com.example.lyubishevtime.request.event.ListFilter;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TimeEventMapper {
    @Insert("INSERT INTO time_event (user_id, tag_id, date, name, start_time, minute) " +
            "VALUES (#{user.id}, #{tag.id}, #{date}, #{name}, #{startTime}, #{minute})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer add(TimeEventEntity timeEventEntity);

    @Update("UPDATE time_event SET tag_id = #{tag.id}, name = #{name}, start_time = #{startTime}," +
            " minute = #{minute}, updated_at = now() WHERE id = #{id} AND user_id = #{user.id}")
    int update(TimeEventEntity timeEventEntity);

    @Delete("DELETE FROM time_event WHERE id = #{id} AND user_id = #{userId}")
    int delete(Integer id, Integer userId);

    List<TimeEventEntity> list(ListFilter filter);

    @Select("SELECT EXISTS (SELECT 1 FROM time_event WHERE tag_id = #{id}  AND user_id = #{user.id})")
    boolean anyEvent(TimeEventTag tag);
}
