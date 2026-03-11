package com.bhawesh.createCode.service.impl;

import com.bhawesh.createCode.dto.auth.UserProfileResponse;
import com.bhawesh.createCode.error.ResourceNotFoundException;
import com.bhawesh.createCode.repository.UserRepository;
import com.bhawesh.createCode.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserProfileResponse getProfile(Long userId) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()->new ResourceNotFoundException("User",username));
    }
}
