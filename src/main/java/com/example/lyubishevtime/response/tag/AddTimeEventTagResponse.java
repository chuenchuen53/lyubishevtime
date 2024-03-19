package com.example.lyubishevtime.response.tag;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddTimeEventTagResponse {
    @NotNull
    private TimeEventTag timeEventTag;
}
