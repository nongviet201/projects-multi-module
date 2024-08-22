package com.nongviet201.cinema.admin.sdk.converter;

import com.nongviet201.cinema.admin.sdk.response.AdminCinemaMarkerResponse;
import com.nongviet201.cinema.admin.sdk.response.AdminCinemaRevenueResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminCinemaRevenueToResponseConverter {

    public AdminCinemaRevenueResponse convert (
        Integer cinemaId,
        String name,
        Integer totalTickets,
        Long totalRevenue,
        List<AdminCinemaRevenueResponse.Manager> managers
    ) {
        return AdminCinemaRevenueResponse.builder()
            .cinemaId(cinemaId)
            .name(name)
            .totalTickets(totalTickets)
            .totalRevenue(totalRevenue)
            .managers(managers)
            .build();
    }
}
