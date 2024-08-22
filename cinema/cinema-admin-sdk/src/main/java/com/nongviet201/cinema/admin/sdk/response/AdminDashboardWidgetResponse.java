package com.nongviet201.cinema.admin.sdk.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminDashboardWidgetResponse {

    private Integer totalTicketsSold;
    private Double lastWeekTicketSoldPercentage;

    private Long totalRevenueWeek;
    private Double lastWeekRevenuePercentage;

    private Long totalRevenueMonth;
    private Double lastMonthRevenuePercentage;

    private Integer totalNewUsers;
    private Double lastWeekNewUserPercentage;

}
