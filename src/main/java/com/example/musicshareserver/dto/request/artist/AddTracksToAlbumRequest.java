package com.example.musicshareserver.dto.request.artist;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class AddTracksToAlbumRequest {

    @NotEmpty(message = "Danh sách bài hát không được để trống")
    private List<Long> trackIds;

    private List<Integer> trackOrder;
}
