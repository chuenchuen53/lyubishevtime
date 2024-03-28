package com.example.lyubishevtime.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeEventEntity {
    private Integer id;
    private Integer userId;
    private Integer tagId;
    private LocalDate date;
    private String name;
    private LocalTime startTime;
    private Integer minute;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}