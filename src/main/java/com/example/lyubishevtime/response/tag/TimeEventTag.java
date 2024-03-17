package com.example.lyubishevtime.response.tag;

import com.example.lyubishevtime.entity.TimeEventTagColor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeEventTag {
    private Integer id;
    private TimeEventTagColor color;
    private String name;
}