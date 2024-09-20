package com.nongviet201.cinema.admin.sdk.service;

import com.nongviet201.cinema.core.service.TransactionService;
import com.nongviet201.cinema.core.service.UserService;
import com.nongviet201.cinema.core.utils.DateTimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@AllArgsConstructor
public class AdminDashboardService {

    private final UserService userService;
    private final TransactionService transactionService;

    public Integer getNumberNewUserByTime(String time) {
        DateTimeUtils.DateTimeRange timeRange = DateTimeUtils.getTimeRange(time);

        return userService.getNewUsersByTimeRange(
            timeRange.getStartDate().toLocalDate(),
            timeRange.getEndDate().toLocalDate()
        ).size();
    }


    public static Double calculatePercentage(int a, int b) {
        if (a == 0 && b == 0) {
            return 0.0;
        }
        if (a > 0 && b == 0) {
            return 100.0;
        }
        if (a == 0 && b > 0) {
            return -100.0;
        }

        double result = ((double) a - b) / b * 100;

        BigDecimal roundedResult = new BigDecimal(result).setScale(2, RoundingMode.HALF_UP);

        return roundedResult.doubleValue();
    }
}
