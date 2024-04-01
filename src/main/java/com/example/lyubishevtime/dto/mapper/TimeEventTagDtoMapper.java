package com.example.lyubishevtime.dto.mapper;

import com.example.lyubishevtime.entity.TimeEventTagEntity;
import com.example.lyubishevtime.response.tag.TimeEventTag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimeEventTagDtoMapper {
    TimeEventTag entityToTimeEventTag(TimeEventTagEntity timeEventTagEntity);
}
