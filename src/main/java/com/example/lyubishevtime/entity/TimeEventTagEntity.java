package com.example.lyubishevtime.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeEventTagEntity {
    private Integer id;
    private Integer userId;
    private TimeEventTagColor color;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}