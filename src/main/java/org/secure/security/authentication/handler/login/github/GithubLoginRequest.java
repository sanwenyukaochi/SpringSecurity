package org.secure.security.authentication.handler.login.github;

public record GithubLoginRequest(
        String code
) {
}