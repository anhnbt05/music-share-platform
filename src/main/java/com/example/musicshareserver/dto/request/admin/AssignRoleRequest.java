package com.example.musicshareserver.dto.request.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request dùng để gán / thay đổi vai trò người dùng")
public class AssignRoleRequest {

    @NotNull(message = "User ID không được để trống")
    @Schema(
            description = "ID của người dùng cần thay đổi vai trò",
            example = "1",
            required = true
    )
    private Long userId;

    @NotBlank(message = "Vai trò mới không được để trống")
    @Schema(
            description = "Vai trò mới của người dùng (USER | ARTIST | ADMIN)",
            example = "ARTIST",
            required = true
    )
    private String newRole;
}
