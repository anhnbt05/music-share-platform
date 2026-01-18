package com.example.musicshareserver.dto.request.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddTrackToPlaylistRequest {
    @NotNull(message = "Track ID không được để trống")
    private Long trackId;
}
