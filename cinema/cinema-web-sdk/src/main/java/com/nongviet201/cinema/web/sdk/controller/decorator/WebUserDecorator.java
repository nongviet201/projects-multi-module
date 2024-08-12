package com.nongviet201.cinema.web.sdk.controller.decorator;

import com.nongviet201.cinema.core.entity.user.User;
import com.nongviet201.cinema.web.sdk.converter.WebUserToResponseConverter;
import com.nongviet201.cinema.web.sdk.response.WebUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WebUserDecorator {

    private final WebUserToResponseConverter converter;
    private final WebDateTimeFormatter timeFormatter;

    public WebUserResponse decorate(
        User user
    ) {
        return converter.convert(
            user.getEmail(),
            user.getFullName(),
            user.getPhoneNumber(),
            user.getAvatar(),
            user.getGender(),
            timeFormatter.formatDateToDDmmYYYY(user.getBirthday())
        );
    }
}
