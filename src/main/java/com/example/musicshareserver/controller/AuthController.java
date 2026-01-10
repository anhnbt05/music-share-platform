package com.example.musicshareserver.controller;

import com.example.musicshareserver.dto.request.*;
import com.example.musicshareserver.dto.response.AuthResponse;
import com.example.musicshareserver.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Authentication APIs")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Đăng nhập", description = "Đăng nhập bằng email và mật khẩu")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authService.login(request));
    }

    @Operation(summary = "Refresh token", description = "Lấy access token mới từ refresh token")
    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(
            @Valid @RequestBody RefreshTokenRequest request
    ) {
        return ResponseEntity.ok(
                authService.refreshToken(request.getRefreshToken())
        );
    }

    @Operation(summary = "Đăng ký", description = "Đăng ký tài khoản mới và gửi OTP xác minh email")
    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        authService.register(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Xác minh OTP đăng ký", description = "Xác minh OTP sau khi đăng ký")
    @PostMapping("/verify-otp")
    public ResponseEntity<Void> verifyOtp(
            @Valid @RequestBody VerifyOtpRequest request
    ) {
        authService.verifyOtp(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Quên mật khẩu", description = "Gửi OTP đặt lại mật khẩu qua email")
    @PostMapping("/forgot-password")
    public ResponseEntity<Void> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequest request
    ) {
        authService.forgotPassword(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Xác minh OTP reset mật khẩu", description = "Xác minh OTP để reset mật khẩu")
    @PostMapping("/verify-reset-otp")
    public ResponseEntity<Void> verifyResetOtp(
            @Valid @RequestBody VerifyResetOtpRequest request
    ) {
        authService.verifyResetOtp(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Đặt lại mật khẩu", description = "Đặt lại mật khẩu mới sau khi xác minh OTP")
    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(
            @Valid @RequestBody ResetPasswordRequest request
    ) {
        authService.resetPassword(request);
        return ResponseEntity.ok().build();
    }
}
