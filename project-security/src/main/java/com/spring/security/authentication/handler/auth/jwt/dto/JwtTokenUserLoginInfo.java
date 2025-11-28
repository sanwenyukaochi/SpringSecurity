package com.spring.security.authentication.handler.auth.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtTokenUserLoginInfo{
    private String sessionId;
    private String username;
    private Long expiredTime;

    public JwtTokenUserLoginInfo(String sessionId, String username) {
        this.sessionId = sessionId;
        this.username = username;
    }
}
