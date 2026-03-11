package com.bhawesh.createCode.service.impl;

import com.bhawesh.createCode.dto.auth.AuthResponse;
import com.bhawesh.createCode.dto.auth.LoginRequest;
import com.bhawesh.createCode.dto.auth.SignUpRequest;
import com.bhawesh.createCode.entity.User;
import com.bhawesh.createCode.error.BadRequestException;
import com.bhawesh.createCode.mapper.UserMapper;
import com.bhawesh.createCode.repository.UserRepository;
import com.bhawesh.createCode.security.AuthUtil;
import com.bhawesh.createCode.security.JwtUserPrincipal;
import com.bhawesh.createCode.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthUtil authUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse signupUser(SignUpRequest request) {
        userRepository.findByUsername(request.username()).ifPresent(user -> {
            throw new BadRequestException("User already exists with username:"+request.username());
        });
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        String accessToken = authUtil.generateAccessToken(savedUser);
        return new AuthResponse(accessToken,userMapper.toUserProfileResponseFromUser(user));
    }

    @Override
    public AuthResponse loginUser(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(),request.password())
        );

        User user = (User) authentication.getPrincipal();
        String accessToken = authUtil.generateAccessToken(user);
        return new AuthResponse(accessToken,userMapper.toUserProfileResponseFromUser(user));

    }

    public Long getCurrentLoginUserId(){
        JwtUserPrincipal user = (JwtUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        assert user != null;
        return user.userId();
    }


}
