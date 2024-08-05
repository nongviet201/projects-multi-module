package com.nongviet201.cinema.web.sdk.controller.service;

import com.nongviet201.cinema.core.response.VerifyResponse;
import com.nongviet201.cinema.core.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WebVerifyService {

    private final AuthService authService;

    public VerifyResponse verifyRegister(
        String token
    ) {
        return authService.confirmRegistration(token);
    }

    public Boolean verifyForgotPassword(
        String tokenForgotPassword
    ) {
        return authService.confirmForgotPassword(tokenForgotPassword);
    }
}
