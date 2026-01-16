package com.example.musicshareserver.dto.request.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request xử lý đơn đăng ký nghệ sĩ")
public class ProcessArtistApplicationRequest {

    @NotBlank(message = "Hành động không được để trống")
    @Schema(
            description = "Hành động xử lý (APPROVE | REJECT)",
            example = "APPROVE",
            required = true
    )
    private String action;

    @Schema(
            description = "Lý do từ chối (chỉ dùng khi action = REJECT)",
            example = "Không đủ thông tin xác minh"
    )
    private String rejectionReason;
}
