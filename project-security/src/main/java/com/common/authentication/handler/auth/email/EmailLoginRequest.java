package com.common.authentication.handler.auth.email;

public record EmailLoginRequest(
        String email,
        String password
) {}