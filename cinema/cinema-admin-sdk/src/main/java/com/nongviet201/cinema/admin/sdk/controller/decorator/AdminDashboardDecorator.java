package com.nongviet201.cinema.admin.sdk.controller.decorator;

import com.nongviet201.cinema.admin.sdk.converter.AdminDashboardWidgetToResponseConvert;
import com.nongviet201.cinema.admin.sdk.response.AdminDashboardWidgetResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminDashboardDecorator {

    private final AdminDashboardWidgetToResponseConvert widgetConvert;

    public AdminDashboardWidgetResponse widgetDecorate(
        int totalTicketsSold,
        double lastWeekTicketSoldPercentage,
        long totalRevenueWeek,
        double lastWeekRevenuePercentage,
        long totalRevenueMonth,
        double lastMonthRevenueMonthPercentage,
        int totalNewUsers,
        double lastWeekNewUserPercentage
    ) {
        return widgetConvert.convert(
            totalTicketsSold,
            lastWeekTicketSoldPercentage,
            totalRevenueWeek,
            lastWeekRevenuePercentage,
            totalRevenueMonth,
            lastMonthRevenueMonthPercentage,
            totalNewUsers,
            lastWeekNewUserPercentage
        );
    }
}
