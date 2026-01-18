package com.example.musicshareserver.dto.request.user;

import jakarta.validation.constraints.AssertTrue;
import lombok.Data;

@Data
public class RemoveTrackFromPlaylistRequest {
    @AssertTrue(message = "Vui lòng xác nhận xóa bài hát khỏi playlist")
    private boolean confirm;
}
