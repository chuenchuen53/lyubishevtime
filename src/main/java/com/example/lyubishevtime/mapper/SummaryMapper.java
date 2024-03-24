package com.example.lyubishevtime.mapper;

import com.example.lyubishevtime.entity.TagSummaryInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

public interface SummaryMapper {
    @Select("SELECT tet.id AS tag_id, tet.name AS tag_name, tet.color AS color, SUM(te.minute) AS total_minutes " +
            "FROM time_event_tag tet LEFT JOIN time_event te ON te.tag_id = tet.id " +
            "WHERE tet.user_id = #{userId} AND te.date BETWEEN #{from} AND #{to} " +
            "GROUP BY tet.id, tet.name, tet.color")
    List<TagSummaryInfoEntity> getSummary(@Param("userId") Integer userId, @Param("from") LocalDate from,
                                          @Param("to") LocalDate to);
}
