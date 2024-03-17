package com.example.lyubishevtime.controller;

import com.example.lyubishevtime.auth.JwtHelper;
import com.example.lyubishevtime.entity.AppUser;
import com.example.lyubishevtime.entity.TimeEventTag;
import com.example.lyubishevtime.entity.TimeEventTagOrder;
import com.example.lyubishevtime.exception.ApiException;
import com.example.lyubishevtime.request.tag.AddTimeEventTagRequest;
import com.example.lyubishevtime.request.tag.ReorderTimeEventTagRequest;
import com.example.lyubishevtime.request.tag.UpdateTimeEventTagRequest;
import com.example.lyubishevtime.response.tag.AddTimeEventTagResponse;
import com.example.lyubishevtime.response.tag.ListTimeEventTagResponse;
import com.example.lyubishevtime.service.api.TimeEventTagService;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class TimeEventTagController {
    private final TimeEventTagService timeEventTagService;
    private final JwtHelper jwtHelper;


    public TimeEventTagController(TimeEventTagService timeEventTagService, JwtHelper jwtHelper) {
        this.timeEventTagService = timeEventTagService;
        this.jwtHelper = jwtHelper;
    }

    @PostMapping("time-event-tag")
    public AddTimeEventTagResponse addTimeEventTag(@Validated @RequestBody AddTimeEventTagRequest req,
                                                   @RequestHeader("Authorization") String authorizationHeader) {
        int userId = jwtHelper.getUserId(authorizationHeader).intValue();
        AppUser user = new AppUser();
        user.setId(userId);
        TimeEventTag timeEventTag = TimeEventTag.builder()
                .name(req.getName())
                .color(req.getColor())
                .user(user)
                .build();
        return timeEventTagService.addTimeEventTag(timeEventTag);
    }

    @GetMapping("time-event-tag")
    public ListTimeEventTagResponse listTimeEventTag(@RequestHeader("Authorization") String authorizationHeader) {
        int userId = jwtHelper.getUserId(authorizationHeader).intValue();
        return timeEventTagService.listTimeEventTag(userId);
    }

    @PutMapping("time-event-tag")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Validated @RequestBody UpdateTimeEventTagRequest req,
                       @RequestHeader("Authorization") String authorizationHeader) {
        int userId = jwtHelper.getUserId(authorizationHeader).intValue();
        AppUser user = new AppUser();
        user.setId(userId);
        TimeEventTag timeEventTag = TimeEventTag.builder()
                .id(req.getId())
                .name(req.getName())
                .color(req.getColor())
                .user(user)
                .build();
        boolean success = timeEventTagService.update(timeEventTag);
        if (!success) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Time event tag not found");
        }
    }

    @DeleteMapping("time-event-tag/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Min(1) @PathVariable Integer id,
                       @RequestHeader("Authorization") String authorizationHeader) {
        int userId = jwtHelper.getUserId(authorizationHeader).intValue();
        boolean success = timeEventTagService.delete(id, userId);
        if (!success) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Time event tag not found");
        }
    }

    @PostMapping("time-event-tag/reorder")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void reorder(@Validated @RequestBody ReorderTimeEventTagRequest req,
                        @RequestHeader("Authorization") String authorizationHeader) {
        int userId = jwtHelper.getUserId(authorizationHeader).intValue();
        AppUser user = new AppUser();
        user.setId(userId);
        TimeEventTagOrder timeEventTagOrder = TimeEventTagOrder.builder()
                .appUser(user)
                .tagIds(req.getTagIds())
                .build();
        boolean success = timeEventTagService.reorder(timeEventTagOrder);
        if (!success) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Time event tag not found");
        }
    }

}
