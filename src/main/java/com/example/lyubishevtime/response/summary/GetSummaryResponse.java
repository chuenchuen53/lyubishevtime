package com.example.lyubishevtime.response.summary;

import com.example.lyubishevtime.entity.TimeEventTagColor;
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
public class GetSummaryResponse {
    @NotNull
    List<TagInfo> tagInfos;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TagInfo {
        @NotNull
        private Integer tagId;

        @NotNull
        private String tagName;

        @NotNull
        private TimeEventTagColor color;

        @NotNull
        private Integer totalMinutes;
    }
}
