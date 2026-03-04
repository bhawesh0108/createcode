package com.bhawesh.createCode.service.impl;

import com.bhawesh.createCode.dto.auth.AuthResponse;
import com.bhawesh.createCode.dto.auth.LoginRequest;
import com.bhawesh.createCode.dto.auth.SignUpRequest;
import com.bhawesh.createCode.entity.User;
import com.bhawesh.createCode.error.BadRequestException;
import com.bhawesh.createCode.mapper.UserMapper;
import com.bhawesh.createCode.repository.UserRepository;
import com.bhawesh.createCode.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse signupUser(SignUpRequest request) {
        userRepository.findByUsername(request.username()).ifPresent(user -> {
            throw new BadRequestException("User already exists with username:"+request.username());
        });
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new AuthResponse("dummyToken",userMapper.toUserProfileResponseFromUser(user));
    }

    @Override
    public AuthResponse loginUser(LoginRequest request) {
        return null;
    }
}
