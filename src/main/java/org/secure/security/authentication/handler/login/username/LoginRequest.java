package org.secure.security.authentication.handler.login.username;

public record LoginRequest(
        String username,
        String password
) {
}