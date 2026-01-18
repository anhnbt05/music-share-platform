package com.example.musicshareserver.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateReportRequest {
    @NotNull(message = "ID đối tượng không được để trống")
    private Long targetId;

    @NotBlank(message = "Loại đối tượng không được để trống")
    // Consider adding enum validation if needed, for now String to match simple implementation
    private String targetType;

    @NotBlank(message = "Lý do không được để trống")
    private String reason;
}
