package com.springframework.security.dto;

public record TokenPair(String accessToken, String refreshToken) {
}