package com.common.authentication.handler.auth.jwt.constant;

import java.util.concurrent.TimeUnit;

public final class JWTConstants {
    public static final String USER_INFO = "userInfo";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final long tokenExpiredTime = TimeUnit.MINUTES.toMillis(10);
    public static final long refreshTokenExpiredTime = TimeUnit.DAYS.toMillis(30);
}
