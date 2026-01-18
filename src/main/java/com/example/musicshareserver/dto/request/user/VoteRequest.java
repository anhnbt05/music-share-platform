package com.example.musicshareserver.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class VoteRequest {
    @NotBlank(message = "Loại vote không được để trống")
    @Pattern(regexp = "^(UPVOTE|DOWNVOTE)$", message = "Loại vote không hợp lệ")
    private String voteType;
}
