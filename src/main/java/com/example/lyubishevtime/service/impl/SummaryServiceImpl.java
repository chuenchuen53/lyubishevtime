package com.example.lyubishevtime.service.impl;

import com.example.lyubishevtime.entity.TagSummaryInfoEntity;
import com.example.lyubishevtime.mapper.SummaryMapper;
import com.example.lyubishevtime.response.summary.GetSummaryResponse;
import com.example.lyubishevtime.service.api.SummaryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SummaryServiceImpl implements SummaryService {
    private final SummaryMapper summaryMapper;

    public SummaryServiceImpl(SummaryMapper summaryMapper) {
        this.summaryMapper = summaryMapper;
    }

    @Override
    public GetSummaryResponse getSummary(Integer userId, LocalDate from, LocalDate to) {
        List<TagSummaryInfoEntity> summary = summaryMapper.getSummary(userId, from, to);
        List<GetSummaryResponse.TagInfo> tagInfos = summary.stream()
                .map(tagSummaryInfoEntity -> GetSummaryResponse.TagInfo.builder()
                        .tagId(tagSummaryInfoEntity.getTagId())
                        .tagName(tagSummaryInfoEntity.getTagName())
                        .totalMinutes(tagSummaryInfoEntity.getTotalMinutes())
                        .color(tagSummaryInfoEntity.getColor())
                        .build())
                .toList();
        return new GetSummaryResponse(tagInfos);
    }
}
