package com.example.lyubishevtime.mapper;

import com.example.lyubishevtime.entity.TimeEventTagOrderEntity;
import com.example.lyubishevtime.typehandler.IntegerArrayTypeHandler;
import org.apache.ibatis.annotations.*;

public interface TimeEventTagOrderMapper {
    @Select("SELECT tag_ids FROM time_event_tag_order WHERE user_id = #{userId}")
    @Results(value = {
            @Result(property = "tagIds", column = "tag_ids",
                    typeHandler = IntegerArrayTypeHandler.class)
    })
    TimeEventTagOrderEntity findByUserId(@Param("userId") Integer userId);

    @Update("UPDATE time_event_tag_order SET tag_ids = " +
            "#{tagIds, jdbcType=ARRAY, typeHandler=com.example.lyubishevtime.typehandler.IntegerArrayTypeHandler}, " +
            "updated_at = now() WHERE user_id = #{userId}")
    int update(TimeEventTagOrderEntity timeEventTagOrderEntity);

    @Update("UPDATE time_event_tag_order SET tag_ids = array_append(tag_ids, #{tagId}) WHERE user_id = #{userId}")
    int appendTagId(@Param("userId") Integer userId, @Param("tagId") Integer tagId);

    @Update("UPDATE time_event_tag_order SET tag_ids = array_remove(tag_ids, #{tagId}) WHERE user_id = #{userId}")
    int removeTagId(@Param("userId") Integer userId, @Param("tagId") Integer tagId);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO time_event_tag_order (user_id, tag_ids) " +
            "VALUES (#{userId}, " +
            "#{tagIds, jdbcType=ARRAY, typeHandler=com.example.lyubishevtime.typehandler.IntegerArrayTypeHandler})")
    int insert(TimeEventTagOrderEntity timeEventTagOrderEntity);
}
