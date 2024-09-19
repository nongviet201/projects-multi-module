package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.user.TokenConfirm;
import com.nongviet201.cinema.core.entity.user.User;
import com.nongviet201.cinema.core.exception.BadRequestException;
import com.nongviet201.cinema.core.model.enums.bill.VerifyResponseType;
import com.nongviet201.cinema.core.model.enums.user.TokenType;
import com.nongviet201.cinema.core.model.enums.user.UserRole;
import com.nongviet201.cinema.core.repository.TokenConfirmRepository;
import com.nongviet201.cinema.core.repository.UserRepository;
import com.nongviet201.cinema.core.request.ChangePasswordAccountRequest;
import com.nongviet201.cinema.core.request.ChangePasswordMailRequest;
import com.nongviet201.cinema.core.request.LoginRequest;
import com.nongviet201.cinema.core.request.RegisterRequest;
import com.nongviet201.cinema.core.response.VerifyResponse;
import com.nongviet201.cinema.core.service.AuthService;
import com.nongviet201.cinema.core.service.MailService;
import com.nongviet201.cinema.core.service.UserService;
import com.nongviet201.cinema.core.service.UserStatisticService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final HttpSession session;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenConfirmRepository tokenConfirmRepository;
    private final MailService mailService;
    private final UserService userService;
    private final UserStatisticService userStatisticService;

    @Override
    public void login(LoginRequest request) {
        try {
            Authentication authentication = authenticateUser(request);
            setSession(authentication);
        } catch (DisabledException e) {
            throw new BadRequestException("Tài khoản chưa được kích hoạt");
        } catch (AuthenticationException e) {
            throw new BadRequestException("Tài khoản hoặc mật khẩu không chính xác");
        }
    }

    @Override
    public void register(RegisterRequest request) {
        validateRegistration(request);

        User newUser = createUser(request);
        userRepository.save(newUser);

        TokenConfirm token = generateToken(newUser, TokenType.REGISTRATION, 24);
        tokenConfirmRepository.save(token);

        sendRegistrationEmail(newUser, token);

        userStatisticService.createStatistic(newUser);
    }

    @Override
    public VerifyResponse confirmRegistration(String token) {
        return confirmToken(token, TokenType.REGISTRATION);
    }

    @Override
    public void forgotPassword(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new BadRequestException("Email không tồn tại"));

        TokenConfirm token = generateToken(user, TokenType.PASSWORD_RESET, 1);
        tokenConfirmRepository.save(token);

        sendPasswordResetEmail(user, token);
    }

    @Override
    public void changePasswordMail(ChangePasswordMailRequest request) {
        if (request.getPassword().length() < 8) {
            throw new BadRequestException("Mật khẩu phải có ít nhất 8 kí tự");
        }

        if (!request.getPassword().equals(request.getPasswordConfirm())) {
            throw new BadRequestException("Mật khẩu xác nhận không chính xác");
        }

        TokenConfirm tokenConfirm = tokenConfirmRepository.findByTokenAndType(request.getToken(), TokenType.PASSWORD_CHANGE)
            .orElseThrow(() -> new BadRequestException("token không hợp lệ"));

        User user = tokenConfirm.getUser();

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        login(new LoginRequest(user.getEmail(), request.getPassword(), false));
    }

    @Override
    public VerifyResponse confirmForgotPassword(String token) {
        return confirmToken(
            token,
            TokenType.PASSWORD_RESET
        );
    }

    @Override
    public void changePasswordAccount(ChangePasswordAccountRequest request) {
        if (request.getNewPassword().length() < 8) {
            throw new BadRequestException("Mật khẩu phải có ít nhất 8 kí tự");
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException("Mật khẩu xác nhận không chính xác");
        }

        User user = userService.getCurrentUser();

        boolean checkPassword = passwordEncoder.matches(
            request.getOldPassword(),
            user.getPassword()
        );

        if (checkPassword) {
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new BadRequestException("Mật khẩu cũ không chính xác");
        }
    }

    private Authentication authenticateUser(LoginRequest request) {
        UsernamePasswordAuthenticationToken token =
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        return authenticationManager.authenticate(token);
    }

    private void setSession(Authentication authentication) {
        session.setAttribute("MY_SESSION", authentication.getName());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void validateRegistration(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email đã tồn tại");
        }

        if (userRepository.findByPhoneNumber(request.getPhoneNumber()).isPresent()) {
            throw new BadRequestException("Số điện thoại đã tồn tại");
        }

        if (request.getPassword().length() < 8) {
            throw new BadRequestException("Mật khẩu phải từ 8 ký tự");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException("Mật khẩu và xác nhận mật khẩu phải giống nhau");
        }
    }

    private User createUser(RegisterRequest request) {
        String avatar = getAvatarUrl(request.getGender());
        return User.builder()
            .fullName(request.getFullName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .avatar(avatar)
            .phoneNumber(request.getPhoneNumber())
            .birthday(LocalDate.parse(request.getBirthday()))
            .gender(request.getGender())
            .updatedAt(LocalDate.now())
            .createdAt(LocalDate.now())
            .enabled(false)
            .role(UserRole.USER)
            .build();
    }

    private String getAvatarUrl(String gender) {
        return gender.equals("Nữ")
            ? "https://cdn-icons-png.flaticon.com/512/2880/2880587.png"
            : "https://cdn-icons-png.flaticon.com/512/1141/1141797.png";
    }

    private TokenConfirm generateToken(User user, TokenType type, int expiryHours) {
        return TokenConfirm.builder()
            .token(UUID.randomUUID().toString())
            .type(type)
            .createdAt(LocalDateTime.now())
            .expiresAt(LocalDateTime.now().plusHours(expiryHours))
            .user(user)
            .build();
    }

    private void sendRegistrationEmail(User user, TokenConfirm token) {
        Map<String, String> data = createEmailData(user, token);
        mailService.sendMailConfirmRegistration(data);
    }

    public void resendEmail(
        User user,
        String type
    ) {
        TokenConfirm token = generateToken(user, TokenType.REGISTRATION, 24);

        Map<String, String> data = createEmailData(user, token);

        if (type.equalsIgnoreCase("register")) {
            mailService.sendMailConfirmRegistration(data);
        }
        if (type.equalsIgnoreCase("resetPassword")) {
            mailService.sendMailResetPassword(data);
        }
    }

    private void sendPasswordResetEmail(User user, TokenConfirm token) {
        Map<String, String> data = createEmailData(user, token);
        System.out.println("token:" + token);
        mailService.sendMailResetPassword(data);
    }

    private Map<String, String> createEmailData(User user, TokenConfirm token) {
        Map<String, String> data = new HashMap<>();
        data.put("email", user.getEmail());
        data.put("fullName", user.getFullName());
        data.put("token", token.getToken());
        return data;
    }

    private VerifyResponse confirmToken(String token, TokenType type) {
        TokenConfirm tokenConfirm = tokenConfirmRepository.findByTokenAndType(token, type)
            .orElse(null);
        if (tokenConfirm == null) {
            return new VerifyResponse(VerifyResponseType.NOT_FOUND, false, "Link xác thực không tồn tại", null);
        }

        if (tokenConfirm.getConfirmedAt() != null) {
            return new VerifyResponse(VerifyResponseType.USED, false, "Link xác thực đã được sử dụng trước đó", null);
        }

        if (tokenConfirm.getExpiresAt().isBefore(LocalDateTime.now())) {
            return new VerifyResponse(VerifyResponseType.EXPIRED, false, "Link xác thực đã hết hạn", null);
        }

        tokenConfirm.setConfirmedAt(LocalDateTime.now());
        tokenConfirmRepository.save(tokenConfirm);

        if (type == TokenType.PASSWORD_RESET) {
            tokenConfirm.setType(TokenType.PASSWORD_CHANGE);
            tokenConfirmRepository.save(tokenConfirm);
            return new VerifyResponse(VerifyResponseType.CHANGE_PASSWORD, true, "Xác thực Email thành công, vui lòng tạo mật khẩu mới", token);
        } else {
            User user = tokenConfirm.getUser();
            user.setEnabled(true);
            userRepository.save(user);
        }

        return new VerifyResponse(VerifyResponseType.REGISTRATION, true, "Tài khoản của bạn đã đuợc xác thực thành công!!", null);
    }
}

