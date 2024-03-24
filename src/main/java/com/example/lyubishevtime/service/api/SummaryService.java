package com.example.lyubishevtime.service.api;

import com.example.lyubishevtime.response.summary.GetSummaryResponse;

import java.time.LocalDate;

public interface SummaryService {
    GetSummaryResponse getSummary(Integer userId, LocalDate from, LocalDate to);
}
