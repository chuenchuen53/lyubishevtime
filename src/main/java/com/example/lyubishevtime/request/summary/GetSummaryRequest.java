package com.example.lyubishevtime.request.summary;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetSummaryRequest {
    @NotNull
    private LocalDate from;

    @NotNull
    private LocalDate to;
}
