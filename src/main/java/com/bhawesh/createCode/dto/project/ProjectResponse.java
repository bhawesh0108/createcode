package com.bhawesh.createCode.dto.project;

import com.bhawesh.createCode.dto.auth.UserProfileResponse;

import java.time.Instant;

public record ProjectResponse(
        Long id,
        String name,
        Instant createdAt,
        Instant updatedAt
) {
}
