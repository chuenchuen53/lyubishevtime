package com.example.lyubishevtime.response.tag;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnyEventResponse {
    @NonNull
    private Boolean anyEvent;
}
