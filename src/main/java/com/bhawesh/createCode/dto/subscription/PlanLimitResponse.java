package com.bhawesh.createCode.dto.subscription;

public record PlanLimitResponse(
        String planName,
        int maxProjects,
        int maxTokensPerDay,
        boolean unlimitedAi
) {
}
