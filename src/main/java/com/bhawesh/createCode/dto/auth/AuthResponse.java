package com.bhawesh.createCode.dto.auth;

public record AuthResponse(
        String token,
        UserProfileResponse user
) {

}
