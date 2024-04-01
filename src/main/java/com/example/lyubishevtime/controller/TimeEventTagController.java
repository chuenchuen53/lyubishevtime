package com.example.lyubishevtime.controller;

import com.example.lyubishevtime.entity.TimeEventTagEntity;
import com.example.lyubishevtime.entity.TimeEventTagOrderEntity;
import com.example.lyubishevtime.exception.ApiException;
import com.example.lyubishevtime.request.tag.AddTimeEventTagRequest;
import com.example.lyubishevtime.request.tag.ReorderTimeEventTagRequest;
import com.example.lyubishevtime.request.tag.UpdateTimeEventTagRequest;
import com.example.lyubishevtime.response.tag.AddTimeEventTagResponse;
import com.example.lyubishevtime.response.tag.AnyEventResponse;
import com.example.lyubishevtime.response.tag.ListTimeEventTagResponse;
import com.example.lyubishevtime.service.api.TimeEventTagService;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class TimeEventTagController {
    private final TimeEventTagService timeEventTagService;

    public TimeEventTagController(TimeEventTagService timeEventTagService) {
        this.timeEventTagService = timeEventTagService;
    }

    @PostMapping("time-event-tag")
    public AddTimeEventTagResponse addTimeEventTag(@Validated @RequestBody AddTimeEventTagRequest req,
                                                   @RequestAttribute("userId") Integer userId) {
        TimeEventTagEntity timeEventTagEntity = TimeEventTagEntity.builder()
                .userId(userId)
                .name(req.getName())
                .color(req.getColor())
                .build();
        return timeEventTagService.addTimeEventTag(timeEventTagEntity);
    }

    @GetMapping("time-event-tag")
    public ListTimeEventTagResponse listTimeEventTag(@RequestAttribute("userId") Integer userId) {
        return timeEventTagService.listTimeEventTag(userId);
    }

    @PutMapping("time-event-tag")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTimeEventTag(@Validated @RequestBody UpdateTimeEventTagRequest req,
                                   @RequestAttribute("userId") Integer userId) {
        TimeEventTagEntity timeEventTagEntity = TimeEventTagEntity.builder()
                .userId(userId)
                .id(req.getId())
                .name(req.getName())
                .color(req.getColor())
                .build();
        boolean success = timeEventTagService.update(timeEventTagEntity);
        if (!success) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Time event tag not found");
        }
    }

    @DeleteMapping("time-event-tag/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTimeEventTag(@Min(1) @PathVariable Integer id, @RequestAttribute("userId") Integer userId) {
        boolean success = timeEventTagService.delete(id, userId);
        if (!success) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Time event tag not found");
        }
    }

    @PostMapping("time-event-tag/reorder")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void reorderTimeEventTag(@Validated @RequestBody ReorderTimeEventTagRequest req,
                                    @RequestAttribute("userId") Integer userId) {
        TimeEventTagOrderEntity timeEventTagOrderEntity = TimeEventTagOrderEntity.builder()
                .userId(userId)
                .tagIds(req.getTagIds())
                .build();
        boolean success = timeEventTagService.reorder(timeEventTagOrderEntity);
        if (!success) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Time event tag not found");
        }
    }

    @GetMapping("time-event-tag/any-event/{id}")
    public AnyEventResponse anyEvent(@Min(1) @PathVariable Integer id, @RequestAttribute("userId") Integer userId) {
        TimeEventTagEntity tag = TimeEventTagEntity.builder()
                .id(id)
                .userId(userId)
                .build();
        return new AnyEventResponse(timeEventTagService.anyEvent(tag));
    }
}
