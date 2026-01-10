package com.example.musicshareserver.security;

import com.example.musicshareserver.entity.User;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${JWT_SECRET}")
    private String secret;

    private static final long ACCESS_TOKEN_EXPIRE = 15 * 60 * 1000;
    private static final long REFRESH_TOKEN_EXPIRE = 7 * 24 * 60 * 60 * 1000;


    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }


    public boolean validateRefreshToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Refresh token đã hết hạn");
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("Refresh token không hợp lệ");
        }
    }


    public Integer getUserIdFromRefreshToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return Integer.parseInt(claims.getSubject());
    }


    public Long getUserIdFromToken(String token) {
        return Long.parseLong(
                Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject()
        );
    }
}
