package com.example.musicshareserver.dto.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request dùng để đặt lại mật khẩu mới cho người dùng")
public class ResetPasswordRequest {

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    @Schema(
            description = "Email của người dùng cần đặt lại mật khẩu",
            example = "user@gmail.com",
            required = true
    )
    private String email;

    @NotBlank(message = "Mật khẩu mới không được để trống")
    @Size(min = 6, message = "Mật khẩu mới phải có ít nhất 6 ký tự")
    @Schema(
            description = "Mật khẩu mới của người dùng",
            example = "newPassword123",
            required = true
    )
    private String newPassword;
}
