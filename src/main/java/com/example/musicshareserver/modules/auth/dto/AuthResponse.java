package com.example.musicshareserver.modules.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import com.example.musicshareserver.modules.user.dto.UserDTO;

@Schema(description = "Phản hồi xác thực")
public class AuthResponse {

    @Schema(description = "Token JWT cho các yêu cầu đã xác thực", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    @Schema(description = "Thông báo phản hồi", example = "Đăng nhập thành công")
    private String message;

    @Schema(description = "Thông tin người dùng")
    private UserDTO user;

    public AuthResponse() {}

    public AuthResponse(String token, String message, UserDTO user) {
        this.token = token;
        this.message = message;
        this.user = user;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public UserDTO getUser() { return user; }
    public void setUser(UserDTO user) { this.user = user; }
}