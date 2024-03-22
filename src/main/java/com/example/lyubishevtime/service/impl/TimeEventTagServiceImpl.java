package com.example.lyubishevtime.service.impl;

import com.example.lyubishevtime.entity.TimeEventTag;
import com.example.lyubishevtime.entity.TimeEventTagOrder;
import com.example.lyubishevtime.mapper.TimeEventTagMapper;
import com.example.lyubishevtime.mapper.TimeEventTagOrderMapper;
import com.example.lyubishevtime.response.tag.AddTimeEventTagResponse;
import com.example.lyubishevtime.response.tag.ListTimeEventTagResponse;
import com.example.lyubishevtime.service.api.TimeEventTagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class TimeEventTagServiceImpl implements TimeEventTagService {
    private final TimeEventTagMapper timeEventTagMapper;
    private final TimeEventTagOrderMapper timeEventTagOrderMapper;

    public TimeEventTagServiceImpl(TimeEventTagMapper timeEventTagMapper,
                                   TimeEventTagOrderMapper timeEventTagOrderMapper) {
        this.timeEventTagMapper = timeEventTagMapper;
        this.timeEventTagOrderMapper = timeEventTagOrderMapper;
    }

    @Override
    @Transactional
    public AddTimeEventTagResponse addTimeEventTag(TimeEventTag timeEventTag) {
        timeEventTagMapper.addTimeEventTag(timeEventTag);
        var tag = com.example.lyubishevtime.response.tag.TimeEventTag
                .builder()
                .id(timeEventTag.getId())
                .name(timeEventTag.getName())
                .color(timeEventTag.getColor())
                .build();
        timeEventTagOrderMapper.appendTagId(timeEventTag.getUser().getId(), timeEventTag.getId());
        return new AddTimeEventTagResponse(tag);
    }

    @Override
    public ListTimeEventTagResponse listTimeEventTag(Integer userId) {
        var tagEntityList = (timeEventTagMapper.listTimeEventTag(userId));
        var tagList = tagEntityList.stream().map((x) -> com.example.lyubishevtime.response.tag.TimeEventTag
                        .builder()
                        .id(x.getId())
                        .name(x.getName())
                        .color(x.getColor())
                        .build())
                .toList();
        var tagOrderEntity = timeEventTagOrderMapper.findByUserId(userId);
        List<Integer> tagOrder = tagOrderEntity == null ? Collections.emptyList() : tagOrderEntity.getTagIds();
        return ListTimeEventTagResponse.builder()
                .timeEventTags(tagList)
                .timeEventTagOrder(tagOrder)
                .build();
    }

    @Override
    public boolean update(TimeEventTag timeEventTag) {
        int count = timeEventTagMapper.update(timeEventTag);
        return count > 0;
    }

    @Override
    @Transactional
    public boolean delete(Integer id, Integer userId) {
        int count = timeEventTagMapper.delete(id, userId);
        if (count == 0) return false;
        timeEventTagOrderMapper.removeTagId(userId, id);
        return true;
    }

    @Override
    public boolean reorder(TimeEventTagOrder timeEventTagOrder) {
        int count = timeEventTagOrderMapper.update(timeEventTagOrder);
        return count > 0;
    }
}
