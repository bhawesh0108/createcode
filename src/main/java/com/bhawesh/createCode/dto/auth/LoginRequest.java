package com.bhawesh.createCode.dto.auth;

public record LoginRequest(
        String email,
        String password
) {

}
