package com.example.lyubishevtime.response.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUserResponse {
    @NotNull
    private Integer id;

    @NotNull
    private String username;

    @NotNull
    private String nickname;

    @Schema(nullable = true, requiredMode = Schema.RequiredMode.REQUIRED)
    private String profilePic;

    @NotNull
    private String token;
}
