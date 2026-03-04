package com.bhawesh.createCode.dto.member;

import com.bhawesh.createCode.enums.ProjectRole;

import java.time.Instant;

public record MemberResponse(
        Long userId,
        String name,
        String username,
        ProjectRole role,
        Instant invitedAt
) {
}
