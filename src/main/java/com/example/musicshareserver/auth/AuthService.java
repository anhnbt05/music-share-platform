package com.example.musicshareserver.auth;

import com.example.musicshareserver.auth.dto.AuthResponse;
import com.example.musicshareserver.auth.dto.LoginRequest;
import com.example.musicshareserver.auth.dto.RegisterRequest;
import com.example.musicshareserver.common.JwtService;
import com.example.musicshareserver.entity.User;
import com.example.musicshareserver.user.UserRepository;
import com.example.musicshareserver.user.dto.UserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse(null, "Email đã tồn tại", null);
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setRole("USER");
        user.setCreatedAt(LocalDateTime.now());
        user.setIsDeleted(false);

        User savedUser = userRepository.save(user);
        String token = jwtService.generateToken(savedUser);

        return new AuthResponse(token, "Đăng ký thành công", mapToDTO(savedUser));
    }

    public AuthResponse login(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if (userOptional.isEmpty() || !passwordEncoder.matches(request.getPassword(), userOptional.get().getPassword())) {
            return new AuthResponse(null, "Thông tin đăng nhập không chính xác", null);
        }

        User user = userOptional.get();
        if (user.getIsDeleted()) {
            return new AuthResponse(null, "Tài khoản đã bị xóa", null);
        }

        String token = jwtService.generateToken(user);
        return new AuthResponse(token, "Đăng nhập thành công", mapToDTO(user));
    }

    private UserDTO mapToDTO(User user) {
        return new UserDTO(user.getId(), user.getEmail(), user.getName(), user.getRole());
    }
}