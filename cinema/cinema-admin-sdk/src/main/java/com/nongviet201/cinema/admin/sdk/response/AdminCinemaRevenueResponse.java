package com.nongviet201.cinema.admin.sdk.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AdminCinemaRevenueResponse {

    private String name;
    private Integer cinemaId;
    private Integer totalTickets;
    private Long totalRevenue;
    private Integer kpiPercent;
    private Long totalKpi;
    private List<Manager> managers;

    @Getter
    @Builder
    public static class Manager {
        private String name;
        private Integer userId;
    }
}
