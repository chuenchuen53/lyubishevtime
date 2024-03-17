package com.example.lyubishevtime.request.tag;

import com.example.lyubishevtime.entity.TimeEventTagColor;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTimeEventTagRequest {
    @NotNull
    @Min(1)
    private Integer id;

    @NotNull
    private TimeEventTagColor color;

    @NotBlank
    private String name;
}
