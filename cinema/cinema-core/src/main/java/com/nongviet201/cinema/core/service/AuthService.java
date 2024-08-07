package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.request.ChangePasswordAccountRequest;
import com.nongviet201.cinema.core.request.ChangePasswordMailRequest;
import com.nongviet201.cinema.core.request.LoginRequest;
import com.nongviet201.cinema.core.request.RegisterRequest;
import com.nongviet201.cinema.core.response.VerifyResponse;

public interface AuthService {
    void login(LoginRequest request);

    void register(RegisterRequest request);

    VerifyResponse confirmRegistration(String token);

    void forgotPassword(String email);

    void changePasswordMail(ChangePasswordMailRequest request);

    Boolean confirmForgotPassword(String token);

    void changePasswordAccount(ChangePasswordAccountRequest request);
}
