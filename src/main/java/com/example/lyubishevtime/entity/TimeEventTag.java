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
public class TimeEventTag {
    private Integer id;
    private AppUser user;
    private TimeEventTagColor color;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}