package com.example.lyubishevtime.request.user;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public class UpdateNicknameRequest {
    @NotBlank()
    @Max(30)
    private String nickname;
}
