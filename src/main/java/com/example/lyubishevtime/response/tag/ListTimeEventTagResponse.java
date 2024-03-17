package com.example.lyubishevtime.response.tag;

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
    private List<TimeEventTag> timeEventTags;
    private List<Integer> timeEventTagOrder;
}
