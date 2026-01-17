package com.example.musicshareserver.dto.request.artist;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateProfileRequest {

    private String bio;

    @Size(max = 255, message = "URL ảnh không được quá 255 ký tự")
    private String photoUrl;

    private String socialLinks;

    @Size(max = 255, message = "Nghệ danh không được quá 255 ký tự")
    private String stageName;
}
