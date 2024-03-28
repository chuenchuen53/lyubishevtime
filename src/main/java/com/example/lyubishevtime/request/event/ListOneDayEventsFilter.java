package com.example.lyubishevtime.request.event;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListOneDayEventsFilter {
    @NotNull
    private Integer userId;

    @NotNull
    private LocalDate date;

    private List<Integer> tagIds;
}
