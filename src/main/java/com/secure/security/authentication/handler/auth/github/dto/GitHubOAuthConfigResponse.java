package com.secure.security.authentication.handler.auth.github.dto;

public record GitHubOAuthConfigResponse(
        String clientId,
        String redirectUri
) {
}
