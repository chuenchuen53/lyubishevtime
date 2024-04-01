package com.example.lyubishevtime.repository.filters;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListEventsFilter {
    @NotNull
    private Integer userId;

    @NotNull
    private Integer tagId;

    @NotNull
    private Integer page;

    @NotNull
    private Integer pageSize;

    public Integer getLimit() {
        return pageSize + 1;
    }

    public Integer getOffset() {
        return (page - 1) * pageSize;
    }
}
