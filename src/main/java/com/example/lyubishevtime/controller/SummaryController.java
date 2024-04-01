package com.example.lyubishevtime.controller;

import com.example.lyubishevtime.response.summary.GetSummaryResponse;
import com.example.lyubishevtime.service.api.SummaryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class SummaryController {
    private final SummaryService summaryService;

    public SummaryController(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    @GetMapping("summary/{from}/{to}")
    public GetSummaryResponse getSummary(
            @PathVariable LocalDate from,
            @PathVariable LocalDate to,
            @RequestAttribute("userId") Integer userId) {
        return summaryService.getSummary(userId, from, to);
    }
}
