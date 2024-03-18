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
public class TimeEventTagOrder {
    private Integer id;
    private AppUser appUser;
    private List<Integer> tagIds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
