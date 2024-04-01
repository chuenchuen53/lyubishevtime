package com.example.lyubishevtime.dto.mapper;

import com.example.lyubishevtime.entity.TimeEventEntity;
import com.example.lyubishevtime.response.event.TimeEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimeEventDtoMapper {
    TimeEvent entityToTimeEvent(TimeEventEntity entity);
}
