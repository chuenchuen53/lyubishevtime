package com.example.lyubishevtime.response.tag;

import com.example.lyubishevtime.entity.TimeEventTagColor;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeEventTag {
    @NotNull
    private Integer id;

    @NotNull
    private TimeEventTagColor color;
    
    @NotNull
    private String name;
}