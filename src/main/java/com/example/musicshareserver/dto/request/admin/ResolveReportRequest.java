package com.example.musicshareserver.dto.request.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request xử lý báo cáo vi phạm")
public class ResolveReportRequest {

    @NotBlank(message = "Ghi chú xử lý không được để trống")
    @Schema(
            description = "Nội dung ghi chú khi xử lý báo cáo",
            example = "Đã cảnh cáo người dùng và gỡ nội dung vi phạm",
            required = true
    )
    private String resolutionNotes;
}
