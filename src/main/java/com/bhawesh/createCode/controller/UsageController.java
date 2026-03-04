package com.bhawesh.createCode.controller;

import com.bhawesh.createCode.dto.subscription.PlanLimitResponse;
import com.bhawesh.createCode.dto.subscription.UsageTodayResponse;
import com.bhawesh.createCode.service.UsageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/api/usage")
public class UsageController {

    private final UsageService usageService;

    @GetMapping("/today")
    public ResponseEntity<UsageTodayResponse> getTodayUsage(){
        Long userId = 1L;
        return ResponseEntity.ok(usageService.getTodayUsage(userId));
    }

    @GetMapping("/limits")
    public ResponseEntity<PlanLimitResponse> getPlanLimit(){
        Long userId = 1L;
        return ResponseEntity.ok(usageService.getCurrentSubscriptionPlanLimit(userId));
    }
}
