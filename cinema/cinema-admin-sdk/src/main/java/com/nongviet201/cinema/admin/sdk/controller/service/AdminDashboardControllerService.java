package com.nongviet201.cinema.admin.sdk.controller.service;

import com.nongviet201.cinema.admin.sdk.controller.decorator.AdminDashboardDecorator;
import com.nongviet201.cinema.admin.sdk.response.AdminDashboardWidgetResponse;
import com.nongviet201.cinema.admin.sdk.service.AdminDashboardService;
import com.nongviet201.cinema.admin.sdk.service.AdminRevenueService;
import com.nongviet201.cinema.core.entity.bill.Translation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminDashboardControllerService {

    private final AdminDashboardDecorator decorator;
    private final AdminDashboardService dashboardService;
    private final AdminRevenueService revenueService;

    public AdminDashboardWidgetResponse getDashboardWidget() {

        List<Translation> weekRevenue = revenueService.getTotalRevenueByTime("week");
        List<Translation> lastWeekRevenue = revenueService.getTotalRevenueByTime("lastWeek");

        List<Translation> monthRevenue = revenueService.getTotalRevenueByTime("month");
        List<Translation> lastMonthRevenue = revenueService.getTotalRevenueByTime("lastMonth");


        Double totalLastWeekTicketSoldPercent = AdminDashboardService.calculatePercentage(
            weekRevenue.size(),
            lastWeekRevenue.size()
        );

        Double totalLastWeekRevenuePercent = AdminDashboardService.calculatePercentage(
            weekRevenue.stream().mapToInt(e -> (int) e.getBill().getTotalPrice()).sum(),
            lastWeekRevenue.stream().mapToInt(e -> (int) e.getBill().getTotalPrice()).sum()
        );

        Double totalLastMonthRevenuePercent = AdminDashboardService.calculatePercentage(
            monthRevenue.stream().mapToInt(e -> (int) e.getBill().getTotalPrice()).sum(),
            lastMonthRevenue.stream().mapToInt(e -> (int) e.getBill().getTotalPrice()).sum()
        );



        return decorator.widgetDecorate(
            weekRevenue.size(),
            totalLastWeekTicketSoldPercent,

            weekRevenue.stream().mapToLong(e -> e.getBill().getTotalPrice()).sum(),
            totalLastWeekRevenuePercent,

            monthRevenue.stream().mapToLong(e -> e.getBill().getTotalPrice()).sum(),
            totalLastMonthRevenuePercent,

            dashboardService.getNumberNewUserByTime("week"),
            AdminDashboardService.calculatePercentage(
                dashboardService.getNumberNewUserByTime("week"),
                dashboardService.getNumberNewUserByTime("lastWeek")
            )
        );
    }
}
