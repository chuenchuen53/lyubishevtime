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
    @NotNull
    private Integer userId;
    
    @NotNull
    private LocalDate date;

    private List<Integer> tagIds;
}
