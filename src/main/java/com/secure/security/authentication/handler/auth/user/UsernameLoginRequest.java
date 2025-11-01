package com.secure.security.authentication.handler.auth.user;

public record UsernameLoginRequest(
        String username,
        String password
) {
}