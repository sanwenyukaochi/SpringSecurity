package com.common.authentication.handler.auth.user;

public record UsernameLoginRequest(
        String username,
        String password
) {
}