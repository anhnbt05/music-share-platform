package com.example.musicshareserver.dto.request.artist;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateAlbumRequest {

    @Size(max = 255, message = "Tiêu đề không được quá 255 ký tự")
    private String title;

    private String description;

    @Size(max = 255, message = "URL ảnh bìa không được quá 255 ký tự")
    private String coverUrl;
}
