package com.example.lyubishevtime.response.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddTimeEventTagResponse {
    private TimeEventTag timeEventTag;
}
