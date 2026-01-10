package com.example.musicshareserver.controller;

import com.example.musicshareserver.dto.request.*;
import com.example.musicshareserver.dto.response.ApiResponse;
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
    public ResponseEntity<ApiResponse<AuthResponse>> login(
            @Valid @RequestBody LoginRequest request
    ) {
        AuthResponse authResponse = authService.login(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Đăng nhập thành công", authResponse));
    }

    @Operation(summary = "Refresh token", description = "Lấy access token mới từ refresh token")
    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<AuthResponse>> refreshToken(
            @Valid @RequestBody RefreshTokenRequest request
    ) {
        AuthResponse authResponse = authService.refreshToken(request.getRefreshToken());
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy access token thành công", authResponse));
    }

    @Operation(summary = "Đăng ký", description = "Đăng ký tài khoản mới và gửi OTP xác minh email")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        authService.register(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Đăng ký thành công, vui lòng xác minh OTP", null));
    }

    @Operation(summary = "Xác minh OTP đăng ký", description = "Xác minh OTP sau khi đăng ký")
    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponse<Void>> verifyOtp(
            @Valid @RequestBody VerifyOtpRequest request
    ) {
        authService.verifyOtp(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Xác minh OTP thành công", null));
    }

    @Operation(summary = "Quên mật khẩu", description = "Gửi OTP đặt lại mật khẩu qua email")
    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse<Void>> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequest request
    ) {
        authService.forgotPassword(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "OTP đặt lại mật khẩu đã được gửi qua email", null));
    }

    @Operation(summary = "Xác minh OTP reset mật khẩu", description = "Xác minh OTP để reset mật khẩu")
    @PostMapping("/verify-reset-otp")
    public ResponseEntity<ApiResponse<Void>> verifyResetOtp(
            @Valid @RequestBody VerifyResetOtpRequest request
    ) {
        authService.verifyResetOtp(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Xác minh OTP reset thành công", null));
    }

    @Operation(summary = "Đặt lại mật khẩu", description = "Đặt lại mật khẩu mới sau khi xác minh OTP")
    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<Void>> resetPassword(
            @Valid @RequestBody ResetPasswordRequest request
    ) {
        authService.resetPassword(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Đặt lại mật khẩu thành công", null));
    }
}
