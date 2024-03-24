package com.example.lyubishevtime.request.event;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListFilter {
    private List<Integer> tagIds;
    @NotNull
    private LocalDate from;
    @NotNull
    private LocalDate to;
}
