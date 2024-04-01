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
public class ListOneDayTimeEventResponse {
    @NotNull
    List<TimeEvent> timeEvents;
}
