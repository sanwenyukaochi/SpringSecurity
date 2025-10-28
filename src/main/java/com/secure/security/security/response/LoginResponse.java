package com.secure.security.security.response;

import java.util.List;

public record LoginResponse(
        String username,
        List<String> roles,
        String jwtToken
) {
}