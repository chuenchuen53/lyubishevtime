package com.example.lyubishevtime.response.event;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeEvent {
    @NotNull
    private Integer id;

    @NotNull
    private Integer tagId;

    @NotNull
    private LocalDate date;

    @NotNull
    private String name;

    @NotNull
    @Schema(type = "String", pattern = "HH:mm:SS")
    private LocalTime startTime;

    @NotNull
    private Integer minute;
}
