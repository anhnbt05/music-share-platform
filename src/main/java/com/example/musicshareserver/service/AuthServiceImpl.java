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

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ApiException("Sai email hoặc mật khẩu"));

        if (!bCryptUtil.matches(request.getPassword(), user.getPassword())) {
            throw new ApiException("Sai email hoặc mật khẩu");
        }

        if (!Boolean.TRUE.equals(user.getIsActive())) {
            throw new ApiException("Tài khoản chưa xác minh OTP");
        }

        if (Boolean.TRUE.equals(user.getIsDeleted())) {
            throw new ApiException("Tài khoản đã bị khóa");
        }

        String accessToken = jwtTokenProvider.generateAccessToken(user);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user);

        return new AuthResponse(accessToken, refreshToken);
    }

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

    @Override
    public void register(RegisterRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        String name = request.getName();

        String hashedPassword = bCryptUtil.hash(password);

        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null) {
            if (Boolean.TRUE.equals(user.getIsActive())) {
                throw new ApiException("Email đã tồn tại và đã kích hoạt.");
            }

            user.setName(name);
            user.setPassword(hashedPassword);
            user.setIsActive(false);
            user.setIsDeleted(false);
            userRepository.save(user);

        } else {
            user = User.builder()
                    .email(email)
                    .password(hashedPassword)
                    .name(name)
                    .role("USER")
                    .isActive(false)
                    .isDeleted(false)
                    .createdAt(LocalDateTime.now())
                    .build();

            userRepository.save(user);
        }

        String otp = String.valueOf((int) (100000 + Math.random() * 900000));
        String hashedOtp = bCryptUtil.hash(otp);

        redisService.setEx("otp:" + email, 300, hashedOtp);
        redisService.setEx("otp_attempts:" + email, 300, "0");

        mailService.sendOtpEmail(
                email,
                "Xác minh tài khoản",
                otp,
                name,
                "register",
                "Xác minh tài khoản",
                "Mã OTP để xác minh tài khoản của bạn là",
                "Mã OTP này dùng để xác nhận email khi đăng ký tài khoản mới."
        );
    }

    @Override
    public void verifyOtp(VerifyOtpRequest request) {
        String key = "otp:register:" + request.getEmail();
        String hashedOtp = redisService.get(key);

        if (hashedOtp == null) throw new ApiException("OTP đã hết hạn");

        int attempts = Integer.parseInt(redisService.get("otp:register:attempts:" + request.getEmail()) != null
                ? redisService.get("otp:register:attempts:" + request.getEmail())
                : "0");

        if (attempts >= 5) {
            redisService.del(key, "otp:register:attempts:" + request.getEmail());
            throw new ApiException("Bạn đã nhập sai OTP quá nhiều lần");
        }

        if (!bCryptUtil.matches(request.getOtp(), hashedOtp)) {
            redisService.setEx("otp:register:attempts:" + request.getEmail(), 300, String.valueOf(attempts + 1));
            throw new ApiException("OTP không đúng (" + (attempts + 1) + "/5 lần)");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ApiException("User không tồn tại"));

        user.setIsActive(true);
        userRepository.save(user);

        redisService.del(key, "otp:register:attempts:" + request.getEmail());
    }

    @Override
    public void forgotPassword(ForgotPasswordRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ApiException("Email không tồn tại"));

        String otp = OtpUtil.generate();
        redisService.setEx("otp:reset:" + user.getEmail(), 300, bCryptUtil.hash(otp));
        redisService.setEx("otp:reset:attempts:" + user.getEmail(), 300, "0");

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

    @Override
    public void verifyResetOtp(VerifyResetOtpRequest request) {
        String key = "otp:reset:" + request.getEmail();
        String hashedOtp = redisService.get(key);

        if (hashedOtp == null) throw new ApiException("OTP đã hết hạn");

        int attempts = Integer.parseInt(redisService.get("otp:reset:attempts:" + request.getEmail()) != null
                ? redisService.get("otp:reset:attempts:" + request.getEmail())
                : "0");

        if (attempts >= 5) {
            redisService.del(key, "otp:reset:attempts:" + request.getEmail());
            throw new ApiException("Bạn đã nhập sai OTP quá nhiều lần");
        }

        if (!bCryptUtil.matches(request.getOtp(), hashedOtp)) {
            redisService.setEx("otp:reset:attempts:" + request.getEmail(), 300, String.valueOf(attempts + 1));
            throw new ApiException("OTP không đúng (" + (attempts + 1) + "/5 lần)");
        }

        redisService.setEx("otp:reset:verified:" + request.getEmail(), 600, "true");
        redisService.del(key, "otp:reset:attempts:" + request.getEmail());
    }

    @Override
    public void resetPassword(ResetPasswordRequest request) {
        String verifyKey = "otp:reset:verified:" + request.getEmail();
        String verified = redisService.get(verifyKey);

        if (verified == null) throw new ApiException("Bạn chưa xác minh OTP");

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ApiException("User không tồn tại"));

        user.setPassword(bCryptUtil.hash(request.getNewPassword()));
        userRepository.save(user);

        redisService.del(verifyKey);
    }
}

