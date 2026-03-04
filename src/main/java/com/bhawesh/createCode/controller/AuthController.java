package com.bhawesh.createCode.controller;

import com.bhawesh.createCode.dto.auth.AuthResponse;
import com.bhawesh.createCode.dto.auth.LoginRequest;
import com.bhawesh.createCode.dto.auth.SignUpRequest;
import com.bhawesh.createCode.dto.auth.UserProfileResponse;
import com.bhawesh.createCode.service.AuthService;
import com.bhawesh.createCode.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUp(@RequestBody SignUpRequest request){
        return ResponseEntity.ok(authService.signupUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> logIn(@RequestBody  LoginRequest request){
        return ResponseEntity.ok(authService.loginUser(request));
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile(){
        Long userId = 1L;
        return ResponseEntity.ok(userService.getProfile(userId));
    }
}
