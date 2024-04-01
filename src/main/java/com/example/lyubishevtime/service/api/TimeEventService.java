package com.example.lyubishevtime.service.api;


import com.example.lyubishevtime.entity.TimeEventEntity;
import com.example.lyubishevtime.repository.filters.ListEventsFilter;
import com.example.lyubishevtime.repository.filters.ListOneDayEventsFilter;
import com.example.lyubishevtime.response.event.AddTimeEventResponse;
import com.example.lyubishevtime.response.event.ListOneDayTimeEventResponse;
import com.example.lyubishevtime.response.event.ListTimeEventByTagIdResponse;

public interface TimeEventService {
    AddTimeEventResponse add(TimeEventEntity timeEventEntity);

    boolean update(TimeEventEntity timeEventEntity);

    boolean delete(Integer id, Integer userId);

    ListOneDayTimeEventResponse listOneDay(ListOneDayEventsFilter listOneDayEventsFilter);

    ListTimeEventByTagIdResponse listByTagId(ListEventsFilter listEventsFilter);
}
