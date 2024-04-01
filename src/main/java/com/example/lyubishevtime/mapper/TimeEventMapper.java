package com.example.lyubishevtime.mapper;

import com.example.lyubishevtime.entity.TimeEventEntity;
import com.example.lyubishevtime.entity.TimeEventTagEntity;
import com.example.lyubishevtime.repository.filters.ListEventsFilter;
import com.example.lyubishevtime.repository.filters.ListOneDayEventsFilter;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TimeEventMapper {
    @Insert("INSERT INTO time_event (user_id, tag_id, date, name, start_time, minute) " +
            "VALUES (#{userId}, #{tagId}, #{date}, #{name}, #{startTime}, #{minute})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer add(TimeEventEntity timeEventEntity);

    @Update("UPDATE time_event SET tag_id = #{tagId}, name = #{name}, start_time = #{startTime}," +
            " minute = #{minute}, updated_at = now() WHERE id = #{id} AND user_id = #{userId}")
    int update(TimeEventEntity timeEventEntity);

    @Delete("DELETE FROM time_event WHERE id = #{id} AND user_id = #{userId}")
    int delete(@Param("id") Integer id, @Param("userId") Integer userId);

    List<TimeEventEntity> listOneDay(ListOneDayEventsFilter filter);

    List<TimeEventEntity> listByTagId(ListEventsFilter filter);

    @Select("SELECT EXISTS (SELECT 1 FROM time_event WHERE tag_id = #{id} AND user_id = #{userId})")
    boolean anyEventForTag(TimeEventTagEntity tag);
}
