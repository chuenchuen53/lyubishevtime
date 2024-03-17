package com.example.lyubishevtime.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeEvent {
    private Integer id;
    private AppUser user;
    private TimeEventTag tag;
    private String name;
    private Integer minute;
    private LocalDate date;
    private Integer eventOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}