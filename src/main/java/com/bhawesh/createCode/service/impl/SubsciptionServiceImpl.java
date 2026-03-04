package com.bhawesh.createCode.service.impl;

import com.bhawesh.createCode.dto.subscription.CheckoutRequest;
import com.bhawesh.createCode.dto.subscription.CheckoutResponse;
import com.bhawesh.createCode.dto.subscription.PortalResponse;
import com.bhawesh.createCode.dto.subscription.SubscriptionResponse;
import com.bhawesh.createCode.service.SubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class SubsciptionServiceImpl implements SubscriptionService {
    @Override
    public SubscriptionResponse getSubscriptioInfo(Long userId) {
        return null;
    }

    @Override
    public CheckoutResponse createCheckoutSessionUrl(Long userId, CheckoutRequest request) {
        return null;
    }

    @Override
    public PortalResponse openCustomerPortal(long userId) {
        return null;
    }
}
