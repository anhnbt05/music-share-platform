package com.example.musicshareserver.modules.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Yêu cầu đăng nhập người dùng")
public class LoginRequest {

    @Schema(description = "Địa chỉ email người dùng", example = "user@example.com", required = true)
    @NotBlank(message = "Email là bắt buộc")
    private String email;

    @Schema(description = "Mật khẩu người dùng", example = "password123", required = true)
    @NotBlank(message = "Mật khẩu là bắt buộc")
    private String password;

    public LoginRequest() {}

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}