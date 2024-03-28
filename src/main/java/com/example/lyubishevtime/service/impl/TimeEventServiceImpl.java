package com.example.lyubishevtime.service.impl;

import com.example.lyubishevtime.entity.TimeEventEntity;
import com.example.lyubishevtime.entity.TimeEventTagEntity;
import com.example.lyubishevtime.exception.ApiException;
import com.example.lyubishevtime.mapper.TimeEventMapper;
import com.example.lyubishevtime.mapper.TimeEventTagMapper;
import com.example.lyubishevtime.request.event.ListEventsFilter;
import com.example.lyubishevtime.request.event.ListOneDayEventsFilter;
import com.example.lyubishevtime.response.event.AddTimeEventResponse;
import com.example.lyubishevtime.response.event.ListOneDayTimeEventResponse;
import com.example.lyubishevtime.response.event.ListTimeEventResponse;
import com.example.lyubishevtime.response.event.TimeEvent;
import com.example.lyubishevtime.service.api.TimeEventService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeEventServiceImpl implements TimeEventService {
    private final TimeEventMapper timeEventMapper;
    private final TimeEventTagMapper timeEventTagMapper;

    public TimeEventServiceImpl(TimeEventMapper timeEventMapper, TimeEventTagMapper timeEventTagMapper) {
        this.timeEventMapper = timeEventMapper;
        this.timeEventTagMapper = timeEventTagMapper;
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
        TimeEvent event = TimeEvent.builder()
                .id(timeEventEntity.getId())
                .tagId(timeEventEntity.getTagId())
                .date(timeEventEntity.getDate())
                .name(timeEventEntity.getName())
                .startTime(timeEventEntity.getStartTime())
                .minute(timeEventEntity.getMinute())
                .build();
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
        List<TimeEventEntity> timeEventEntities = timeEventMapper.listWithOneDay(listOneDayEventsFilter);
        List<TimeEvent> timeEvents = timeEventEntities.stream()
                .map((entity) -> TimeEvent.builder()
                        .id(entity.getId())
                        .tagId(entity.getTagId())
                        .date(entity.getDate())
                        .name(entity.getName())
                        .startTime(entity.getStartTime())
                        .minute(entity.getMinute())
                        .build())
                .toList();
        ListOneDayTimeEventResponse response = new ListOneDayTimeEventResponse();
        response.setTimeEvents(timeEvents);
        return response;
    }

    @Override
    public ListTimeEventResponse list(ListEventsFilter listEventsFilter) {
        List<TimeEventEntity> timeEventEntities = timeEventMapper.listAllTagEvents(listEventsFilter);
        boolean haveNext = timeEventEntities.size() > listEventsFilter.getPageSize();
        if (haveNext) {
            timeEventEntities.remove(timeEventEntities.size() - 1);
        }
        List<TimeEvent> timeEvents = timeEventEntities.stream()
                .map((entity) -> TimeEvent.builder()
                        .id(entity.getId())
                        .tagId(entity.getTagId())
                        .date(entity.getDate())
                        .name(entity.getName())
                        .startTime(entity.getStartTime())
                        .minute(entity.getMinute())
                        .build())
                .toList();
        ListTimeEventResponse response = new ListTimeEventResponse();
        response.setTimeEvents(timeEvents);
        response.setHaveNext(haveNext);
        return response;
    }
}
