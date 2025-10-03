package com.springframework.security.util;


import com.springframework.security.dto.TokenPair;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final RSAUtil  rsaUtil;
    public String getJwtFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public TokenPair generateTokenPairFromUsername(String username) {
        String accessToken = generateTokenFromUsername(username);
        String refreshToken = generateRefreshToken(username);
        return new TokenPair(accessToken, refreshToken);
    }

    public String generateTokenFromUsername(String username) {
        long expirationMs = 24 * 60 * 60 * 1000;
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(rsaUtil.getPrivateKey(), Jwts.SIG.RS256)
                .compact();
    }

    public String generateRefreshToken(String username) {
        long refreshExpirationMs = 7L * 24 * 60 * 60 * 1000;
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + refreshExpirationMs))
                .signWith(rsaUtil.getPrivateKey(), Jwts.SIG.RS256)
                .compact();
    }

    public boolean validateRefreshToken(String token) {
        return validateJwtToken(token);
    }

    public String refreshAccessToken(String refreshToken) {
        if (validateJwtToken(refreshToken)) {
            String username = getUserNameFromJwtToken(refreshToken);
            return generateTokenFromUsername(username);
        }
        throw new JwtException("Refresh Token 无效或已过期");
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .verifyWith(rsaUtil.getPublicKey())
                .build().parseSignedClaims(token)
                .getPayload().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().verifyWith(rsaUtil.getPublicKey()).build().parseSignedClaims(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.error("无效的JWT令牌: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT令牌已过期: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("不支持的JWT令牌: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT声明字符串为空: {}", e.getMessage());
        }
        return false;
    }
}