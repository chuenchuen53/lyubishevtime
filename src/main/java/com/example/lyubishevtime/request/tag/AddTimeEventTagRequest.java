package com.example.lyubishevtime.request.tag;

import com.example.lyubishevtime.entity.TimeEventTagColor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddTimeEventTagRequest {
    @NotNull
    private TimeEventTagColor color;

    @NotBlank
    @Size(max = 255)
    private String name;
}
