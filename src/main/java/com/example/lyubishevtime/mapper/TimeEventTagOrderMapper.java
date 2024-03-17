package com.example.lyubishevtime.mapper;

import com.example.lyubishevtime.entity.TimeEventTagOrder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TimeEventTagOrderMapper {
    @Select("SELECT tag_ids FROM time_event_tag_order WHERE user_id = #{userId}")
    TimeEventTagOrder findByUserId(@Param("userId") Integer userId);

    int upsert(TimeEventTagOrder timeEventTagOrder);
}
