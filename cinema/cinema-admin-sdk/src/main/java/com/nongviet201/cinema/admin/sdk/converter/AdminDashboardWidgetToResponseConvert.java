package com.nongviet201.cinema.admin.sdk.converter;

import com.nongviet201.cinema.admin.sdk.response.AdminDashboardWidgetResponse;
import org.springframework.stereotype.Service;

@Service
public class AdminDashboardWidgetToResponseConvert {

    public AdminDashboardWidgetResponse convert(
        int totalTicketsSold,
        double lastWeekTicketSoldPercentage,
        long totalRevenueWeek,
        double lastWeekRevenuePercentage,
        long totalRevenueMonth,
        double lastMonthRevenueMonthPercentage,
        int totalNewUsers,
        double lastWeekNewUserPercentage
    ) {
        return AdminDashboardWidgetResponse.builder()
            .totalTicketsSold(totalTicketsSold)
            .lastWeekTicketSoldPercentage(lastWeekTicketSoldPercentage)
            .totalRevenueWeek(totalRevenueWeek)
            .lastWeekRevenuePercentage(lastWeekRevenuePercentage)
            .totalRevenueMonth(totalRevenueMonth)
            .lastMonthRevenuePercentage(lastMonthRevenueMonthPercentage)
            .totalNewUsers(totalNewUsers)
            .lastWeekNewUserPercentage(lastWeekNewUserPercentage)
            .build();
    }
}
