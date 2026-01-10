package com.example.musicshareserver.service;

import com.example.musicshareserver.dto.request.*;
import com.example.musicshareserver.dto.response.AuthResponse;
import com.example.musicshareserver.entity.User;
import com.example.musicshareserver.exception.ApiException;
import com.example.musicshareserver.repository.UserRepository;
import com.example.musicshareserver.security.JwtTokenProvider;
import com.example.musicshareserver.util.BCryptUtil;
import com.example.musicshareserver.util.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptUtil bCryptUtil;
    private final RedisService redisService;
    private final MailService mailService;

    /* ================= LOGIN ================= */
    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ApiException("Sai email hoặc mật khẩu"));

        if (!bCryptUtil.matches(request.getPassword(), user.getPassword())) {
            throw new ApiException("Sai email hoặc mật khẩu");
        }

        if (!user.isActive()) {
            throw new ApiException("Tài khoản chưa xác minh OTP");
        }

        String accessToken = jwtTokenProvider.generateAccessToken(user);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user);

        return new AuthResponse(accessToken, refreshToken);
    }

    /* ================= REFRESH TOKEN ================= */
    @Override
    public AuthResponse refreshToken(String refreshToken) {
        if (!jwtTokenProvider.validateRefreshToken(refreshToken)) {
            throw new ApiException("Refresh token không hợp lệ");
        }

        Integer userId = jwtTokenProvider.getUserIdFromRefreshToken(refreshToken);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException("User không tồn tại"));

        String newAccessToken = jwtTokenProvider.generateAccessToken(user);
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(user);

        return new AuthResponse(newAccessToken, newRefreshToken);
    }

    /* ================= REGISTER ================= */
    @Override
    public void register(RegisterRequest request) {

        userRepository.findByEmail(request.getEmail())
                .ifPresent(u -> {
                    throw new ApiException("Email đã tồn tại");
                });

        User user = User.builder()
                .email(request.getEmail())
                .password(bCryptUtil.hash(request.getPassword()))
                .name(request.getName())
                .role("USER")
                .isActive(false)
                .isDeleted(false)
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);

        String otp = OtpUtil.generate();

        redisService.setEx(
                "otp:register:" + user.getEmail(),
                300,
                bCryptUtil.hash(otp)
        );

        mailService.sendOtpEmail(
                user.getEmail(),
                "Xác minh tài khoản",
                otp,
                user.getName(),
                "Xác minh email",
                "Xác minh email",
                "Mã OTP để xác minh tài khoản của bạn là",
                "Mã OTP này dùng để xác nhận email khi đăng ký tài khoản mới."
        );
    }

    /* ================= VERIFY REGISTER OTP ================= */
    @Override
    public void verifyOtp(VerifyOtpRequest request) {

        String key = "otp:register:" + request.getEmail();
        String hashedOtp = redisService.get(key);

        if (hashedOtp == null) {
            throw new ApiException("OTP đã hết hạn");
        }

        if (!bCryptUtil.matches(request.getOtp(), hashedOtp)) {
            throw new ApiException("OTP không đúng");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ApiException("User không tồn tại"));

        user.setActive(true);
        userRepository.save(user);

        redisService.del(key);
    }

    /* ================= FORGOT PASSWORD ================= */
    @Override
    public void forgotPassword(ForgotPasswordRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ApiException("Email không tồn tại"));

        String otp = OtpUtil.generate();

        redisService.setEx(
                "otp:reset:" + user.getEmail(),
                300,
                bCryptUtil.hash(otp)
        );

        mailService.sendOtpEmail(
                user.getEmail(),
                "Đặt lại mật khẩu",
                otp,
                user.getName(),
                "Reset mật khẩu",
                "Reset mật khẩu",
                "Mã OTP để đặt lại mật khẩu của bạn là",
                "Mã OTP này dùng để xác nhận việc đặt lại mật khẩu."
        );
    }

    /* ================= VERIFY RESET OTP ================= */
    @Override
    public void verifyResetOtp(VerifyResetOtpRequest request) {

        String key = "otp:reset:" + request.getEmail();
        String hashedOtp = redisService.get(key);

        if (hashedOtp == null) {
            throw new ApiException("OTP đã hết hạn");
        }

        if (!bCryptUtil.matches(request.getOtp(), hashedOtp)) {
            throw new ApiException("OTP không đúng");
        }

        redisService.setEx(
                "otp:reset:verified:" + request.getEmail(),
                300,
                "true"
        );

        redisService.del(key);
    }

    /* ================= RESET PASSWORD ================= */
    @Override
    public void resetPassword(ResetPasswordRequest request) {

        String verifyKey = "otp:reset:verified:" + request.getEmail();
        String verified = redisService.get(verifyKey);

        if (verified == null) {
            throw new ApiException("Chưa xác minh OTP");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ApiException("User không tồn tại"));

        user.setPassword(bCryptUtil.hash(request.getNewPassword()));
        userRepository.save(user);

        redisService.del(verifyKey);
    }
}
