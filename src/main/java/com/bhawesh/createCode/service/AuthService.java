package com.bhawesh.createCode.service;

import com.bhawesh.createCode.dto.auth.AuthResponse;
import com.bhawesh.createCode.dto.auth.LoginRequest;
import com.bhawesh.createCode.dto.auth.SignUpRequest;
import org.springframework.stereotype.Service;


public interface AuthService {

     AuthResponse signupUser(SignUpRequest request);

    AuthResponse loginUser(LoginRequest request);
}
