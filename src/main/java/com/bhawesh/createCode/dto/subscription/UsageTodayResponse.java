package com.bhawesh.createCode.dto.subscription;

public record UsageTodayResponse(
        int tokensUsed,
        int tokensLimit,
        int previewsLimit,
        int previewsRunning
) {
}
