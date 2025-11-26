package com.common.authentication.handler.auth.github;

public record GitHubLoginRequest(
        String code
) {
}