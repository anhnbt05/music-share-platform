package com.example.musicshareserver.dto.request.artist;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateMusicRequest {

    @Size(max = 255, message = "Tiêu đề không được quá 255 ký tự")
    private String title;

    @Size(max = 255, message = "Thể loại không được quá 255 ký tự")
    private String genre;

    private String description;
}
