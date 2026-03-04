package com.bhawesh.createCode.service;

import com.bhawesh.createCode.dto.auth.UserProfileResponse;
import org.springframework.stereotype.Service;



public interface UserService {

     UserProfileResponse getProfile(Long userId);
}
