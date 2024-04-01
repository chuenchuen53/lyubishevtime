package com.example.lyubishevtime.response.event;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListTimeEventByTagIdResponse {
    @NotNull
    List<TimeEvent> timeEvents;

    @NotNull
    private Boolean haveNext;
}
