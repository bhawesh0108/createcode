package com.bhawesh.createCode.service;

import com.bhawesh.createCode.dto.subscription.PlanLimitResponse;
import com.bhawesh.createCode.dto.subscription.UsageTodayResponse;
import org.springframework.stereotype.Service;



public interface UsageService {
    UsageTodayResponse getTodayUsage(Long userId);

    PlanLimitResponse getCurrentSubscriptionPlanLimit(Long userId);
}
