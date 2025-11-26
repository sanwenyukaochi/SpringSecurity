package com.common.authentication.handler.auth.github.dto;

public record GitHubOAuthConfigResponse(
        String clientId,
        String redirectUri
) {
}
