package com.nongviet201.cinema.core.rest;

import com.nongviet201.cinema.core.request.ChangePasswordRequest;
import com.nongviet201.cinema.core.request.LoginRequest;
import com.nongviet201.cinema.core.request.RegisterRequest;
import com.nongviet201.cinema.core.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthApi {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(
        @RequestBody LoginRequest request
    ) {
        authService.login(request);
        return ResponseEntity.ok("login successfully");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
        @RequestBody RegisterRequest request
    ) {
        authService.register(request);
        return ResponseEntity.ok("register successfully");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(
        @RequestParam(value = "email") String email
    ) {
        authService.forgotPassword(email);
        return ResponseEntity.ok("forget password successfully");
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(
        @RequestBody ChangePasswordRequest request
    ) {
        authService.changePassword(request);
        return ResponseEntity.ok("change password successfully");
    }


}
