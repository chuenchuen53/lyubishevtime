package com.example.lyubishevtime.controller;

import com.example.lyubishevtime.entity.AppUser;
import com.example.lyubishevtime.entity.TimeEventEntity;
import com.example.lyubishevtime.entity.TimeEventTag;
import com.example.lyubishevtime.exception.ApiException;
import com.example.lyubishevtime.request.event.AddTimeEventRequest;
import com.example.lyubishevtime.request.event.ListFilter;
import com.example.lyubishevtime.request.event.UpdateTimeEventRequest;
import com.example.lyubishevtime.response.event.AddTimeEventResponse;
import com.example.lyubishevtime.response.event.ListTimeEventResponse;
import com.example.lyubishevtime.response.tag.ListTimeEventTagResponse;
import com.example.lyubishevtime.service.api.TimeEventService;
import com.example.lyubishevtime.service.api.TimeEventTagService;
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
    private final TimeEventTagService timeEventTagService;

    public TimeEventController(TimeEventService timeEventService, TimeEventTagService timeEventTagService) {
        this.timeEventService = timeEventService;
        this.timeEventTagService = timeEventTagService;
    }

    @PostMapping("time-event")
    public AddTimeEventResponse add(@Validated @RequestBody AddTimeEventRequest request,
                                    @RequestAttribute("userId") Integer userId) {
        AppUser user = AppUser.builder().id(userId).build();
        TimeEventTag tag = TimeEventTag.builder().id(request.getTagId()).build();
        TimeEventEntity entity = TimeEventEntity.builder()
                .user(user)
                .tag(tag)
                .date(request.getDate())
                .name(request.getName())
                .startTime(request.getStartTime())
                .minute(request.getMinute())
                .build();
        return timeEventService.add(entity);
    }

    @PutMapping("time-event")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Validated @RequestBody UpdateTimeEventRequest request,
                       @RequestAttribute("userId") Integer userId) {
        AppUser user = AppUser.builder().id(userId).build();
        TimeEventTag tag = TimeEventTag.builder().id(request.getTagId()).build();
        TimeEventEntity entity = TimeEventEntity.builder()
                .id(request.getId())
                .user(user)
                .tag(tag)
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
    public void delete(@Min(1) @PathVariable Integer id, @RequestAttribute("userId") Integer userId) {
        boolean success = timeEventService.delete(id, userId);
        if (!success) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Time event not found");
        }
    }

    @GetMapping("time-event")
    public ListTimeEventResponse getEvents(
            @RequestParam(required = false) List<Integer> tagIds,
            @RequestParam @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestAttribute("userId") Integer userId) {
        ListFilter filter = ListFilter.builder()
                .tagIds(tagIds)
                .from(from)
                .to(to)
                .build();
        ListTimeEventTagResponse timeEvent = timeEventTagService.listTimeEventTag(userId);
        ListTimeEventResponse response = timeEventService.list(userId, filter);
        response.setTimeEventTags(timeEvent.getTimeEventTags());
        response.setTimeEventTagOrder(timeEvent.getTimeEventTagOrder());
        return response;
    }
}
