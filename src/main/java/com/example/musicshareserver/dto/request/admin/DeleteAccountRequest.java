package com.example.musicshareserver.dto.request.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request xác nhận xóa (khóa) tài khoản")
public class DeleteAccountRequest {

    @NotNull(message = "Vui lòng xác nhận thao tác")
    @Schema(
            description = "Xác nhận xóa tài khoản",
            example = "true",
            required = true
    )
    private Boolean confirm;
}
