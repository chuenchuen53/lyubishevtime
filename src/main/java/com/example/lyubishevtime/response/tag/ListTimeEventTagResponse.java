package com.example.lyubishevtime.response.tag;

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
public class ListTimeEventTagResponse {
    @NotNull
    private List<TimeEventTag> timeEventTags;
    
    @NotNull
    private List<Integer> timeEventTagOrder;
}
