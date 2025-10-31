package org.secure.security.authentication.handler.login.sms;

public record LoginRequest(
        String phone,
        String captcha
) {
}