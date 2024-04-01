package com.example.lyubishevtime.request.event;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTimeEventRequest {
    @NotNull
    @Min(1)
    private Integer id;

    @NotNull
    @Min(1)
    private Integer tagId;

    @NotBlank
    private String name;

    @NotNull
    @Schema(type = "String", pattern = "HH:mm:SS")
    private LocalTime startTime;

    @NotNull
    @Min(1)
    private Integer minute;
}
