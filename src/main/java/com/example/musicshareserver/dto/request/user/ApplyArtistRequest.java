package com.example.musicshareserver.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ApplyArtistRequest {
    @NotBlank(message = "Stage name không được để trống")
    private String stageName;

    private String bio;

    private String photoUrl;

    private String socialLinks;
}
