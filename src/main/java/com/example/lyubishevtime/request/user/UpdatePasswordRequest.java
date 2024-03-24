package com.example.lyubishevtime.request.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordRequest {
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_]{6,16}$")
    private String oldPassword;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_]{6,16}$")
    private String newPassword;
}
