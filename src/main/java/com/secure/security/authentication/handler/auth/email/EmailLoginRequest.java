package com.secure.security.authentication.handler.auth.email;

public record EmailLoginRequest(
        String email,
        String password
) {}