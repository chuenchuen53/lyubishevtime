package com.example.lyubishevtime.request.user;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
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
public class SignupRequest {
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_]{6,16}$")
    private String username;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_]{6,16}$")
    private String password;

    @NotNull
    @NotBlank()
    @Max(30)
    private String nickname;
}
