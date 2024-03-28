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
public class AppUserEntity {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String profilePic;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
