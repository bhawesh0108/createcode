package com.bhawesh.createCode.dto.subscription;

public record PlanResponse(
        Long id,
        String name,
        Integer maxTokensPerDay,
        Integer maxPreviews,
        Boolean unlimitedAI,
        String price
) {
}
