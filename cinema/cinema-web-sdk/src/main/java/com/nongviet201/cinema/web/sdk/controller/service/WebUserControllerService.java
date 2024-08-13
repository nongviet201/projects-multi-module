package com.nongviet201.cinema.web.sdk.controller.service;

import com.nongviet201.cinema.core.entity.user.UserStatistic;
import com.nongviet201.cinema.core.service.UserService;
import com.nongviet201.cinema.core.service.UserStatisticService;
import com.nongviet201.cinema.web.sdk.controller.decorator.WebUserDecorator;
import com.nongviet201.cinema.web.sdk.response.WebUserResponse;
import com.nongviet201.cinema.web.sdk.response.WebUserStatisticResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WebUserControllerService {

    private final WebUserDecorator userDecorator;
    private final UserService userService;
    private final UserStatisticService userStatisticService;

    public WebUserStatisticResponse getUserStatistic() {
        return userDecorator.decorate(
            userStatisticService.getCurrentUserStatistic()
        );
    }

    public WebUserResponse getCurrentUser() {
        return userDecorator.decorate(
            userService.getCurrentUser()
        );
    }
}
