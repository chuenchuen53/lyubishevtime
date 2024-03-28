package com.example.lyubishevtime.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeEventTagOrderEntity {
    private Integer id;
    private Integer userId;
    private List<Integer> tagIds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
