package com.example.lyubishevtime.service.api;

import com.example.lyubishevtime.entity.TimeEventTag;
import com.example.lyubishevtime.entity.TimeEventTagOrder;
import com.example.lyubishevtime.response.tag.AddTimeEventTagResponse;
import com.example.lyubishevtime.response.tag.ListTimeEventTagResponse;

public interface TimeEventTagService {
    AddTimeEventTagResponse addTimeEventTag(TimeEventTag timeEventTag);

    ListTimeEventTagResponse listTimeEventTag(Integer userId);

    boolean update(TimeEventTag timeEventTag);

    boolean delete(Integer id, Integer userId);

    boolean reorder(TimeEventTagOrder timeEventTagOrder);

    boolean anyEvent(TimeEventTag tag);
}
