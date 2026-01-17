package com.example.musicshareserver.dto.request.artist;

import lombok.Data;

@Data
public class AnalyticsFilterRequest {

    private String startDate;
    private String endDate;
    private Long trackId;
}
