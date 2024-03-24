package com.example.lyubishevtime.controller;

import com.example.lyubishevtime.response.summary.GetSummaryResponse;
import com.example.lyubishevtime.service.api.SummaryService;
import org.springframework.web.bind.annotation.*;

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
            @RequestAttribute("userId") Integer userId
    ) {
        return summaryService.getSummary(userId, from, to);
    }
}
