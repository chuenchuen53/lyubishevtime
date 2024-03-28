package com.example.lyubishevtime.service.api;


import com.example.lyubishevtime.entity.TimeEventEntity;
import com.example.lyubishevtime.request.event.ListFilter;
import com.example.lyubishevtime.request.event.ListOneDayFilter;
import com.example.lyubishevtime.response.event.AddTimeEventResponse;
import com.example.lyubishevtime.response.event.ListOneDayTimeEventResponse;
import com.example.lyubishevtime.response.event.ListTimeEventResponse;

public interface TimeEventService {
    AddTimeEventResponse add(TimeEventEntity timeEventEntity);

    boolean update(TimeEventEntity timeEventEntity);

    boolean delete(Integer id, Integer userId);

    ListOneDayTimeEventResponse listOneDay(ListOneDayFilter listOneDayFilter);

    ListTimeEventResponse list(ListFilter listFilter);
}
