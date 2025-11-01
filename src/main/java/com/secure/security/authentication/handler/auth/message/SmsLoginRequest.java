package com.secure.security.authentication.handler.auth.message;

public record SmsLoginRequest(
        String phone,
        String captcha
) {
}