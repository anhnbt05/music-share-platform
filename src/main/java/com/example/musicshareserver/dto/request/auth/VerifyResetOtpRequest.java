package com.example.musicshareserver.dto.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request dùng để xác thực mã OTP đặt lại mật khẩu")
public class VerifyResetOtpRequest {

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    @Schema(
            description = "Email của người dùng yêu cầu đặt lại mật khẩu",
            example = "user@gmail.com",
            required = true
    )
    private String email;

    @NotBlank(message = "Mã OTP không được để trống")
    @Pattern(
            regexp = "^[0-9]{6}$",
            message = "Mã OTP phải gồm 6 chữ số"
    )
    @Schema(
            description = "Mã OTP gồm 6 chữ số dùng để xác thực đặt lại mật khẩu",
            example = "654321",
            required = true
    )
    private String otp;
}
