package com.example.musicshareserver.service;

import com.example.musicshareserver.dto.request.*;
import com.example.musicshareserver.dto.response.AuthResponse;
import com.example.musicshareserver.exception.ApiException;

public interface AuthService {

    AuthResponse login(LoginRequest request);

    AuthResponse refreshToken(String refreshToken);

    void register(RegisterRequest request);

    void verifyOtp(VerifyOtpRequest request);

    void forgotPassword(ForgotPasswordRequest request);

    void verifyResetOtp(VerifyResetOtpRequest request);

    void resetPassword(ResetPasswordRequest request);
}
