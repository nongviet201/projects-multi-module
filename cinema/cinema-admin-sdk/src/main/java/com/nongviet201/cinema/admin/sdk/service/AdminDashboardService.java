package com.nongviet201.cinema.admin.sdk.service;

import com.nongviet201.cinema.admin.sdk.response.AdminDashboardWidgetResponse;
import com.nongviet201.cinema.core.entity.user.User;
import com.nongviet201.cinema.core.service.TranslationService;
import com.nongviet201.cinema.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
@AllArgsConstructor
public class AdminDashboardService {

    private final UserService userService;
    private final TranslationService translationService;

    public Integer getNumberNewUserByTime(
        String time
    ) {
        LocalDate endDate;
        LocalDate startDate;

        if (time.equals("week")) {
            endDate = LocalDate.now();
            startDate =
                endDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

            return userService.getNewUsersByTimeRange(startDate, endDate).size();
        }

        return 0;
    }

}
