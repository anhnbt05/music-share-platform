package com.example.musicshareserver.dto.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request dùng để đăng ký tài khoản người dùng mới")
public class RegisterRequest {

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    @Schema(
            description = "Email của người dùng (dùng để đăng nhập)",
            example = "user@gmail.com",
            required = true
    )
    private String email;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
    @Schema(
            description = "Mật khẩu đăng nhập",
            example = "12345678",
            required = true
    )
    private String password;

    @NotBlank(message = "Tên hiển thị không được để trống")
    @Schema(
            description = "Tên hiển thị của người dùng",
            example = "Nguyễn Văn A",
            required = true
    )
    private String name;
}
