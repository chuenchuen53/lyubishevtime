package com.example.lyubishevtime.response.event;

import com.example.lyubishevtime.response.tag.TimeEventTag;
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
public class ListTimeEventResponse {
    @NotNull
    List<TimeEvent> timeEvents;

    @NotNull
    private List<TimeEventTag> timeEventTags;

    @NotNull
    private List<Integer> timeEventTagOrder;

    @NotNull
    private Boolean haveNext;
}
