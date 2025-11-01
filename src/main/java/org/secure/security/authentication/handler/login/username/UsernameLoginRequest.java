package org.secure.security.authentication.handler.login.username;

public record UsernameLoginRequest(
        String username,
        String password
) {
}