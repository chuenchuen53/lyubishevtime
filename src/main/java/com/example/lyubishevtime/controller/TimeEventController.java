package com.example.lyubishevtime.controller;

import com.example.lyubishevtime.entity.TimeEventEntity;
import com.example.lyubishevtime.exception.ApiException;
import com.example.lyubishevtime.repository.filters.ListEventsFilter;
import com.example.lyubishevtime.repository.filters.ListOneDayEventsFilter;
import com.example.lyubishevtime.request.event.AddTimeEventRequest;
import com.example.lyubishevtime.request.event.UpdateTimeEventRequest;
import com.example.lyubishevtime.response.event.AddTimeEventResponse;
import com.example.lyubishevtime.response.event.ListOneDayTimeEventResponse;
import com.example.lyubishevtime.response.event.ListTimeEventByTagIdResponse;
import com.example.lyubishevtime.service.api.TimeEventService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
public class TimeEventController {

    private final TimeEventService timeEventService;

    public TimeEventController(TimeEventService timeEventService) {
        this.timeEventService = timeEventService;
    }

    @PostMapping("time-event")
    public AddTimeEventResponse addTimeEvent(@Validated @RequestBody AddTimeEventRequest request,
                                             @RequestAttribute("userId") Integer userId) {
        TimeEventEntity entity = TimeEventEntity.builder()
                .userId(userId)
                .tagId(request.getTagId())
                .date(request.getDate())
                .name(request.getName())
                .startTime(request.getStartTime())
                .minute(request.getMinute())
                .build();
        return timeEventService.add(entity);
    }

    @PutMapping("time-event")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTimeEvent(@Validated @RequestBody UpdateTimeEventRequest request,
                                @RequestAttribute("userId") Integer userId) {
        TimeEventEntity entity = TimeEventEntity.builder()
                .id(request.getId())
                .userId(userId)
                .tagId(request.getTagId())
                .name(request.getName())
                .startTime(request.getStartTime())
                .minute(request.getMinute())
                .build();
        boolean success = timeEventService.update(entity);
        if (!success) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Time event not found");
        }
    }

    @DeleteMapping("time-event/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTimeEvent(@Min(1) @PathVariable Integer id, @RequestAttribute("userId") Integer userId) {
        boolean success = timeEventService.delete(id, userId);
        if (!success) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Time event not found");
        }
    }

    @GetMapping("time-event/one-day")
    public ListOneDayTimeEventResponse getOneDayEvents(
            @RequestParam(required = false) List<Integer> tagIds,
            @RequestParam @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestAttribute("userId") Integer userId) {
        ListOneDayEventsFilter filter = ListOneDayEventsFilter.builder()
                .userId(userId)
                .date(date)
                .tagIds(tagIds)
                .build();
        return timeEventService.listOneDay(filter);
    }

    @GetMapping("time-event/all-tag-events/{tagId}/{page}/{pageSize}")
    public ListTimeEventByTagIdResponse getAllTagEvents(
            @Min(1) @PathVariable Integer tagId,
            @Min(1) @PathVariable Integer page,
            @Min(1) @PathVariable Integer pageSize,
            @RequestAttribute("userId") Integer userId) {
        ListEventsFilter filter = ListEventsFilter.builder()
                .userId(userId)
                .tagId(tagId)
                .page(page)
                .pageSize(pageSize)
                .build();
        return timeEventService.listByTagId(filter);
    }
}
