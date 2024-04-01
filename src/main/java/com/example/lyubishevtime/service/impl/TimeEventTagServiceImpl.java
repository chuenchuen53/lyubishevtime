package com.example.lyubishevtime.service.impl;

import com.example.lyubishevtime.dto.mapper.TimeEventTagDtoMapper;
import com.example.lyubishevtime.entity.TimeEventTagEntity;
import com.example.lyubishevtime.entity.TimeEventTagOrderEntity;
import com.example.lyubishevtime.mapper.TimeEventMapper;
import com.example.lyubishevtime.mapper.TimeEventTagMapper;
import com.example.lyubishevtime.mapper.TimeEventTagOrderMapper;
import com.example.lyubishevtime.response.tag.AddTimeEventTagResponse;
import com.example.lyubishevtime.response.tag.ListTimeEventTagResponse;
import com.example.lyubishevtime.response.tag.TimeEventTag;
import com.example.lyubishevtime.service.api.TimeEventTagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class TimeEventTagServiceImpl implements TimeEventTagService {
    private final TimeEventTagMapper timeEventTagMapper;
    private final TimeEventTagOrderMapper timeEventTagOrderMapper;
    private final TimeEventMapper timeEventMapper;
    private final TimeEventTagDtoMapper timeEventTagDtoMapper;

    public TimeEventTagServiceImpl(TimeEventTagMapper timeEventTagMapper,
                                   TimeEventTagOrderMapper timeEventTagOrderMapper, TimeEventMapper timeEventMapper,
                                   TimeEventTagDtoMapper timeEventTagDtoMapper) {
        this.timeEventTagMapper = timeEventTagMapper;
        this.timeEventTagOrderMapper = timeEventTagOrderMapper;
        this.timeEventMapper = timeEventMapper;
        this.timeEventTagDtoMapper = timeEventTagDtoMapper;
    }

    @Override
    @Transactional
    public AddTimeEventTagResponse addTimeEventTag(TimeEventTagEntity timeEventTagEntity) {
        timeEventTagMapper.addTimeEventTag(timeEventTagEntity);
        timeEventTagOrderMapper.appendTagId(timeEventTagEntity.getUserId(), timeEventTagEntity.getId());
        TimeEventTag tag = timeEventTagDtoMapper.entityToTimeEventTag(timeEventTagEntity);
        return new AddTimeEventTagResponse(tag);
    }

    @Override
    public ListTimeEventTagResponse listTimeEventTag(Integer userId) {
        var tagEntityList = (timeEventTagMapper.listTimeEventTag(userId));
        var tagList = tagEntityList.stream().map(timeEventTagDtoMapper::entityToTimeEventTag).toList();
        var tagOrderEntity = timeEventTagOrderMapper.findByUserId(userId);
        List<Integer> tagOrder = tagOrderEntity == null ? Collections.emptyList() : tagOrderEntity.getTagIds();
        return new ListTimeEventTagResponse(tagList, tagOrder);
    }

    @Override
    public boolean update(TimeEventTagEntity timeEventTagEntity) {
        int count = timeEventTagMapper.update(timeEventTagEntity);
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
    public boolean reorder(TimeEventTagOrderEntity timeEventTagOrderEntity) {
        int count = timeEventTagOrderMapper.update(timeEventTagOrderEntity);
        return count > 0;
    }

    @Override
    public boolean anyEvent(TimeEventTagEntity tag) {
        return timeEventMapper.anyEventForTag(tag);
    }
}
