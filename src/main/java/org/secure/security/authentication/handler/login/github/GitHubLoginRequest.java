package org.secure.security.authentication.handler.login.github;

public record GitHubLoginRequest(
        String code
) {
}