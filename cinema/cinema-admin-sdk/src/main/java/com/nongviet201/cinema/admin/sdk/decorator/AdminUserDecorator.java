package com.nongviet201.cinema.admin.sdk.decorator;

import com.nongviet201.cinema.admin.sdk.converter.AdminUserToResponseConverter;
import com.nongviet201.cinema.admin.sdk.response.AdminUserResponse;
import com.nongviet201.cinema.core.entity.user.User;
import com.nongviet201.cinema.core.utils.WebFormatter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminUserDecorator {

    private final AdminUserToResponseConverter converter;
    private final WebFormatter webFormatter;

    public AdminUserResponse decorate (
        User user
    ) {
        return converter.convert(
            user.getFullName(),
            user.getEmail(),
            user.getPhoneNumber(),
            webFormatter.formatDateToDDmmYYYY(user.getBirthday()),
            user.isEnabled(),
            user.getGender(),
            webFormatter.formatDateToDDmmYYYY(user.getCreatedAt()),
            user.getUserStatistic().getPoints(),
            user.getUserStatistic().getTotalSpending(),
            user.getUserStatistic().getUserRank()
        );
    }
}
