package com.example.lyubishevtime.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagSummaryInfoEntity {
    private Integer tagId;
    private String tagName;
    private Integer totalMinutes;
    private TimeEventTagColor color;
}
