package com.example.musicshareserver.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Yêu cầu đăng ký người dùng")
public class RegisterRequest {

    @Schema(description = "Địa chỉ email người dùng", example = "user@example.com", required = true)
    @NotBlank(message = "Email là bắt buộc")
    @Email(message = "Email phải hợp lệ")
    private String email;

    @Schema(description = "Mật khẩu người dùng (tối thiểu 6 ký tự)", example = "password123", required = true)
    @NotBlank(message = "Mật khẩu là bắt buộc")
    @Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
    private String password;

    @Schema(description = "Họ tên người dùng", example = "Nguyễn Văn A", required = true)
    @NotBlank(message = "Họ tên là bắt buộc")
    private String name;

    public RegisterRequest() {}

    public RegisterRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}