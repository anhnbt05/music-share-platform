package com.example.musicshareserver.modules.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class PasswordResetDTO {

    @Schema(description = "Yêu cầu gửi email đặt lại mật khẩu")
    public static class ForgotPasswordRequest {
        @Schema(description = "Email đăng ký tài khoản", required = true, example = "user@example.com")
        @NotBlank(message = "Email là bắt buộc")
        private String email;

        public ForgotPasswordRequest() {}

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }

    @Schema(description = "Yêu cầu đặt lại mật khẩu với token")
    public static class ResetPasswordRequest {
        @Schema(description = "Token đặt lại mật khẩu", required = true)
        @NotBlank(message = "Token là bắt buộc")
        private String token;

        @Schema(description = "Mật khẩu mới", required = true)
        @NotBlank(message = "Mật khẩu mới là bắt buộc")
        private String newPassword;

        @Schema(description = "Xác nhận mật khẩu mới", required = true)
        @NotBlank(message = "Xác nhận mật khẩu là bắt buộc")
        private String confirmPassword;

        public ResetPasswordRequest() {}

        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }
        public String getNewPassword() { return newPassword; }
        public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
        public String getConfirmPassword() { return confirmPassword; }
        public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }
    }

    @Schema(description = "Yêu cầu đổi mật khẩu khi đã đăng nhập")
    public static class ChangePasswordRequest {
        @Schema(description = "Mật khẩu hiện tại", required = true)
        @NotBlank(message = "Mật khẩu hiện tại là bắt buộc")
        private String currentPassword;

        @Schema(description = "Mật khẩu mới", required = true)
        @NotBlank(message = "Mật khẩu mới là bắt buộc")
        private String newPassword;

        @Schema(description = "Xác nhận mật khẩu mới", required = true)
        @NotBlank(message = "Xác nhận mật khẩu là bắt buộc")
        private String confirmPassword;

        public ChangePasswordRequest() {}

        public String getCurrentPassword() { return currentPassword; }
        public void setCurrentPassword(String currentPassword) { this.currentPassword = currentPassword; }
        public String getNewPassword() { return newPassword; }
        public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
        public String getConfirmPassword() { return confirmPassword; }
        public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }
    }
}