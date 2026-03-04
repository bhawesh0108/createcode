package com.bhawesh.createCode.service;

import com.bhawesh.createCode.dto.subscription.CheckoutRequest;
import com.bhawesh.createCode.dto.subscription.CheckoutResponse;
import com.bhawesh.createCode.dto.subscription.PortalResponse;
import com.bhawesh.createCode.dto.subscription.SubscriptionResponse;
import org.springframework.stereotype.Service;


public interface SubscriptionService {
    SubscriptionResponse getSubscriptioInfo(Long userId);

    CheckoutResponse createCheckoutSessionUrl(Long userId, CheckoutRequest request);

    PortalResponse openCustomerPortal(long userId);
}
