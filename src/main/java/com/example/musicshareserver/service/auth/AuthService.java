package com.example.musicshareserver.service.auth;

import com.example.musicshareserver.dto.request.auth.*;
import com.example.musicshareserver.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse login(LoginRequest request);

    AuthResponse refreshToken(String refreshToken);

    void register(RegisterRequest request);

    void verifyOtp(VerifyOtpRequest request);

    void forgotPassword(ForgotPasswordRequest request);

    void verifyResetOtp(VerifyResetOtpRequest request);

    void resetPassword(ResetPasswordRequest request);
}
