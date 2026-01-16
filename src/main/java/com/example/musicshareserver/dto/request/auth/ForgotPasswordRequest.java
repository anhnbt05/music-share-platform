package com.example.musicshareserver.dto.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request dùng để yêu cầu đặt lại mật khẩu (quên mật khẩu)")
public class ForgotPasswordRequest {

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    @Schema(
            description = "Email của người dùng đã đăng ký trong hệ thống",
            example = "user@gmail.com",
            required = true
    )
    private String email;
}
