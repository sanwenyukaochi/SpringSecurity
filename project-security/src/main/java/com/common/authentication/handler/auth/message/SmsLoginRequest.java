package com.common.authentication.handler.auth.message;

public record SmsLoginRequest(
        String phone,
        String captcha
) {
}