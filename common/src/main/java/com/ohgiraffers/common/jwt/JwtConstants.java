package com.ohgiraffers.common.jwt;

public final class JwtConstants {

    private JwtConstants() {
        // 인스턴스 생성 방지
    }

    // JWT Header
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    // JWT Claims
    public static final String CLAIM_USER_ID = "userId";
    public static final String CLAIM_ROLE = "role";
    public static final String CLAIM_EMAIL = "email";

    // Gateway Headers
    public static final String HEADER_USER_ID = "X-User-Id";
    public static final String HEADER_USER_ROLE = "X-User-Role";
}