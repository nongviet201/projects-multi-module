package com.nongviet201.cinema.admin.sdk.service;

import com.nongviet201.cinema.admin.sdk.decorator.AdminUserDecorator;
import com.nongviet201.cinema.admin.sdk.request.UpsertUserRequest;
import com.nongviet201.cinema.admin.sdk.response.AdminUserResponse;
import com.nongviet201.cinema.core.entity.user.User;
import com.nongviet201.cinema.core.exception.BadRequestException;
import com.nongviet201.cinema.core.model.enums.user.UserRole;
import com.nongviet201.cinema.core.service.AuthService;
import com.nongviet201.cinema.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.nongviet201.cinema.core.utils.DateTimeUtils.parseDate;

@Service
@AllArgsConstructor
public class AdminUserService {

    private final UserService userService;
    private final AdminUserDecorator decorator;
    private final AuthService authService;

    public List<AdminUserResponse> getUserDataFilter(
        UpsertUserRequest.UserFilter request
    ) {
        if (!request.getToDate().isEmpty()) {

            return userService.getDataFilter(
                    parseDate(request.getFormDate()),
                    parseDate(request.getToDate())
                ).stream()
                    .filter(u -> u.getRole() == UserRole.USER)
                    .map(decorator::decorate)
                    .toList();
        }

        return userService.getDataFilter(
                parseDate(request.getFormDate()),
                parseDate(request.getFormDate())
            ).stream()
                .filter(u -> u.getRole() == UserRole.USER)
                .map(decorator::decorate)
                .toList();
    }

    public AdminUserResponse findUser(
        UpsertUserRequest.Find request
    ){
        User user;

        if (!request.getPhoneNumber().isEmpty()) {
            user = userService.getUserByPhoneNumber(request.getPhoneNumber());
        } else {
            user = userService.findByEmail(request.getEmail());
        }

        if (user.getRole() != UserRole.USER) {
            throw new BadRequestException("Bạn không có quyền truy cập thông tin tài khoản này");
        }

        return decorator.decorate(user);
    }

    public void updateUser(
        UpsertUserRequest.Update request
    ) {
        User user = userService.findByEmail(request.getOldEmail());

        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmail(request.getEmail());
        user.setFullName(request.getName());
        user.setBirthday(parseDate(request.getBirthDay()));
        user.setGender(request.getGenre());

        userService.save(user);
    }

    public void sendTokenConfirm(
        String email,
        String type
    ) {
        authService.resendEmail(
            userService.findByEmail(email),
            type
        );
    }
}
