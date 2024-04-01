package com.example.lyubishevtime.service.impl;

import com.example.lyubishevtime.dto.mapper.TimeEventDtoMapper;
import com.example.lyubishevtime.entity.TimeEventEntity;
import com.example.lyubishevtime.entity.TimeEventTagEntity;
import com.example.lyubishevtime.exception.ApiException;
import com.example.lyubishevtime.mapper.TimeEventMapper;
import com.example.lyubishevtime.mapper.TimeEventTagMapper;
import com.example.lyubishevtime.repository.filters.ListEventsFilter;
import com.example.lyubishevtime.repository.filters.ListOneDayEventsFilter;
import com.example.lyubishevtime.response.event.AddTimeEventResponse;
import com.example.lyubishevtime.response.event.ListOneDayTimeEventResponse;
import com.example.lyubishevtime.response.event.ListTimeEventByTagIdResponse;
import com.example.lyubishevtime.response.event.TimeEvent;
import com.example.lyubishevtime.service.api.TimeEventService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeEventServiceImpl implements TimeEventService {
    private final TimeEventMapper timeEventMapper;
    private final TimeEventTagMapper timeEventTagMapper;
    private final TimeEventDtoMapper timeEventDtoMapper;

    public TimeEventServiceImpl(TimeEventMapper timeEventMapper, TimeEventTagMapper timeEventTagMapper,
                                TimeEventDtoMapper timeEventDtoMapper) {
        this.timeEventMapper = timeEventMapper;
        this.timeEventTagMapper = timeEventTagMapper;
        this.timeEventDtoMapper = timeEventDtoMapper;
    }

    @Override
    public AddTimeEventResponse add(TimeEventEntity timeEventEntity) {
        TimeEventTagEntity tag = TimeEventTagEntity.builder()
                .id(timeEventEntity.getTagId())
                .userId(timeEventEntity.getUserId())
                .build();
        if (!timeEventTagMapper.isTagExist(tag)) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid Tag");
        }

        timeEventMapper.add(timeEventEntity);
        TimeEvent event = timeEventDtoMapper.entityToTimeEvent(timeEventEntity);
        return new AddTimeEventResponse(event);
    }

    @Override
    public boolean update(TimeEventEntity timeEventEntity) {
        TimeEventTagEntity tag = TimeEventTagEntity.builder()
                .id(timeEventEntity.getTagId())
                .userId(timeEventEntity.getUserId())
                .build();

        if (!timeEventTagMapper.isTagExist(tag)) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid Tag");
        }

        int updated = timeEventMapper.update(timeEventEntity);
        return updated > 0;
    }

    @Override
    public boolean delete(Integer id, Integer userId) {
        int deleted = timeEventMapper.delete(id, userId);
        return deleted > 0;
    }

    @Override
    public ListOneDayTimeEventResponse listOneDay(ListOneDayEventsFilter listOneDayEventsFilter) {
        List<TimeEventEntity> timeEventEntities = timeEventMapper.listOneDay(listOneDayEventsFilter);
        List<TimeEvent> timeEvents = timeEventEntities.stream()
                .map(timeEventDtoMapper::entityToTimeEvent)
                .toList();
        return new ListOneDayTimeEventResponse(timeEvents);
    }

    @Override
    public ListTimeEventByTagIdResponse listByTagId(ListEventsFilter listEventsFilter) {
        List<TimeEventEntity> timeEventEntities = timeEventMapper.listByTagId(listEventsFilter);
        boolean haveNext = timeEventEntities.size() > listEventsFilter.getPageSize();
        if (haveNext) {
            timeEventEntities.remove(timeEventEntities.size() - 1);
        }
        List<TimeEvent> timeEvents = timeEventEntities.stream()
                .map(timeEventDtoMapper::entityToTimeEvent)
                .toList();
        return new ListTimeEventByTagIdResponse(timeEvents, haveNext);
    }
}
