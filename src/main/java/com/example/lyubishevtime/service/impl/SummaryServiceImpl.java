package com.example.lyubishevtime.service.impl;

import com.example.lyubishevtime.dto.mapper.SummaryDtoMapper;
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
    private final SummaryDtoMapper summaryDtoMapper;

    public SummaryServiceImpl(SummaryMapper summaryMapper, SummaryDtoMapper summaryDtoMapper) {
        this.summaryMapper = summaryMapper;
        this.summaryDtoMapper = summaryDtoMapper;
    }

    @Override
    public GetSummaryResponse getSummary(Integer userId, LocalDate from, LocalDate to) {
        List<TagSummaryInfoEntity> summary = summaryMapper.getSummary(userId, from, to);
        List<GetSummaryResponse.TagInfo> tagInfos = summary.stream()
                .map(summaryDtoMapper::entityToTagInfo)
                .toList();
        return new GetSummaryResponse(tagInfos);
    }
}
