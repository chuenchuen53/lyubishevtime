package com.example.lyubishevtime.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateProfilePicRequest {
    @NotBlank
    private String profilePic;
}
