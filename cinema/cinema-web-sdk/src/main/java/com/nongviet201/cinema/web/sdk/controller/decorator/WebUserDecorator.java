package com.nongviet201.cinema.web.sdk.controller.decorator;

import com.nongviet201.cinema.core.entity.user.User;
import com.nongviet201.cinema.core.entity.user.UserStatistic;
import com.nongviet201.cinema.web.sdk.converter.WebUserStatisticToResponseConverter;
import com.nongviet201.cinema.web.sdk.converter.WebUserToResponseConverter;
import com.nongviet201.cinema.web.sdk.response.WebUserResponse;
import com.nongviet201.cinema.web.sdk.response.WebUserStatisticResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WebUserDecorator {

    private final WebUserToResponseConverter userToResponseConverter;
    private final WebUserStatisticToResponseConverter userStatisticToResponseConverter;
    private final WebDateTimeFormatter timeFormatter;

    public WebUserResponse decorate(
        User user
    ) {
        return userToResponseConverter.convert(
            user.getEmail(),
            user.getFullName(),
            user.getPhoneNumber(),
            user.getAvatar(),
            user.getGender(),
            timeFormatter.formatDateToDDmmYYYY(user.getBirthday())
        );
    }

    public WebUserStatisticResponse decorate(
        UserStatistic userStatistic
    ) {
        double currentPercent = calculatePercentage(Math.toIntExact((userStatistic.getTotalSpending())));

        return userStatisticToResponseConverter.converter(
                userStatistic.getPoints(),
                currentPercent,
                Math.toIntExact(userStatistic.getTotalSpending())
            );
    }

    private double calculatePercentage(int currentValue) {
        int maxValue = 4000000;
        double maxPercentage = 90.0;
        return (currentValue / (double) maxValue) * maxPercentage;
    }
}
