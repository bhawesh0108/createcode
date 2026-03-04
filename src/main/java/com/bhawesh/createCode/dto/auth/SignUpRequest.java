package com.bhawesh.createCode.dto.auth;

public record SignUpRequest(
        String username,
        String password,
        String name
) {
}
