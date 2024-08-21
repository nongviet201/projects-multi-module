package com.nongviet201.cinema.admin.sdk.converter;

import com.nongviet201.cinema.admin.sdk.response.AdminCinemaMarkerResponse;
import com.nongviet201.cinema.admin.sdk.response.AdminCinemaRevenueResponse;
import org.springframework.stereotype.Service;

@Service
public class AdminCinemaRevenueToResponseConverter {

    public AdminCinemaRevenueResponse convert (
        String name,
        Integer totalTickets,
        Long totalRevenue,
        Integer kpiPercent,
        Long totalKpi,
        String manager
    ) {
        return AdminCinemaRevenueResponse.builder()
            .name(name)
            .totalTickets(totalTickets)
            .totalRevenue(totalRevenue)
            .kpiPercent(kpiPercent)
            .totalKpi(totalKpi)
            .manager(manager)
            .build();
    }
}
