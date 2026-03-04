package com.bhawesh.createCode.service.impl;

import com.bhawesh.createCode.dto.auth.UserProfileResponse;
import com.bhawesh.createCode.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserProfileResponse getProfile(Long userId) {
        return null;
    }
}
