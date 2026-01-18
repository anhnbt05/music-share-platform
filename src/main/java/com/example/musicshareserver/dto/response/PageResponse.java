package com.example.musicshareserver.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
    private List<T> data;
    private Meta meta;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Meta {
        private int page;
        private int limit;
        private long total;
        private int totalPages;
    }
}
