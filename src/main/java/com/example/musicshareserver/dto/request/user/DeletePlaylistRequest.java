package com.example.musicshareserver.dto.request.user;

import jakarta.validation.constraints.AssertTrue;
import lombok.Data;

@Data
public class DeletePlaylistRequest {
    @AssertTrue(message = "Vui lòng xác nhận xóa playlist")
    private boolean confirm;
}
