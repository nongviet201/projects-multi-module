package com.nongviet201.cinema.admin.sdk.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminCinemaRevenueResponse {
    private String name;
    private Integer totalTickets;
    private Long totalRevenue;
    private Integer kpiPercent;
    private Long totalKpi;
    private String manager;
}
