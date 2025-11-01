package org.secure.security.authentication.handler.login.sms;

public record SmsLoginRequest(
        String phone,
        String captcha
) {
}