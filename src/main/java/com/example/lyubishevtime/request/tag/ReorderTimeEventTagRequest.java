package com.example.lyubishevtime.request.tag;

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
public class ReorderTimeEventTagRequest {
    @NotNull
    private List<Integer> tagIds;
}
