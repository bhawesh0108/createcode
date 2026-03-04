package com.bhawesh.createCode.service;

import com.bhawesh.createCode.dto.subscription.PlanResponse;
import org.springframework.stereotype.Service;


public interface PlanService {
    PlanResponse getAllActivePlans();
}
