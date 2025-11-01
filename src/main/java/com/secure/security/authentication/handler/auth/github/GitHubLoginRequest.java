package com.secure.security.authentication.handler.auth.github;

public record GitHubLoginRequest(
        String code
) {
}