package com.example.lyubishevtime.dto.mapper;

import com.example.lyubishevtime.entity.TagSummaryInfoEntity;
import com.example.lyubishevtime.response.summary.GetSummaryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SummaryDtoMapper {
    
    GetSummaryResponse.TagInfo entityToTagInfo(TagSummaryInfoEntity entity);
}
