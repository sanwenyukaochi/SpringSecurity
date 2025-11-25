package com.spring.security.authentication.handler.auth.github.dto;

public record GitHubOAuthMeta(
        Boolean requiresBinding,
        Long githubId
) {
}
