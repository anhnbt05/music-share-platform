package com.example.musicshareserver.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreatePlaylistRequest {
    @NotBlank(message = "Tên playlist không được để trống")
    private String name;
}
