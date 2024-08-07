package com.nongviet201.cinema.web.sdk.controller.service;

import com.nongviet201.cinema.core.model.entity.user.User;
import com.nongviet201.cinema.core.service.UserService;
import com.nongviet201.cinema.web.sdk.controller.decorator.WebUserDecorator;
import com.nongviet201.cinema.web.sdk.converter.WebUserToResponseConverter;
import com.nongviet201.cinema.web.sdk.response.WebUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WebUserControllerService {

    private final WebUserDecorator userDecorator;
    private final UserService userService;

    public WebUserResponse getCurrentUser() {
        return userDecorator.decorate(
            userService.getCurrentUser()
        );
    }
}
