package com.bhawesh.createCode.service.impl;

import com.bhawesh.createCode.dto.subscription.PlanLimitResponse;
import com.bhawesh.createCode.dto.subscription.UsageTodayResponse;
import com.bhawesh.createCode.service.UsageService;
import org.springframework.stereotype.Service;

@Service
public class UsageServiceImpl implements UsageService {
    @Override
    public UsageTodayResponse getTodayUsage(Long userId) {
        return null;
    }

    @Override
    public PlanLimitResponse getCurrentSubscriptionPlanLimit(Long userId) {
        return null;
    }
}
