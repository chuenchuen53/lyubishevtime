package com.example.lyubishevtime.service.api;

import com.example.lyubishevtime.entity.TimeEventTagEntity;
import com.example.lyubishevtime.entity.TimeEventTagOrderEntity;
import com.example.lyubishevtime.response.tag.AddTimeEventTagResponse;
import com.example.lyubishevtime.response.tag.ListTimeEventTagResponse;

public interface TimeEventTagService {
    AddTimeEventTagResponse addTimeEventTag(TimeEventTagEntity timeEventTagEntity);

    ListTimeEventTagResponse listTimeEventTag(Integer userId);

    boolean update(TimeEventTagEntity timeEventTagEntity);

    boolean delete(Integer id, Integer userId);

    boolean reorder(TimeEventTagOrderEntity timeEventTagOrderEntity);

    boolean anyEvent(TimeEventTagEntity tag);
}
