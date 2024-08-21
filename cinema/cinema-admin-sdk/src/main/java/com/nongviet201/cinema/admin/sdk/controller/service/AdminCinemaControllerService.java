package com.nongviet201.cinema.admin.sdk.controller.service;

import com.nongviet201.cinema.admin.sdk.controller.decorator.AdminCinemaMarkerDecorator;
import com.nongviet201.cinema.admin.sdk.response.AdminCinemaMarkerResponse;
import com.nongviet201.cinema.admin.sdk.response.AdminCinemaRevenueResponse;
import com.nongviet201.cinema.admin.sdk.service.AdminCinemaService;
import com.nongviet201.cinema.core.entity.cinema.Cinema;
import com.nongviet201.cinema.core.model.enums.bill.BillStatus;
import com.nongviet201.cinema.core.service.CinemaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminCinemaControllerService {

    private final CinemaService cinemaService;
    private final AdminCinemaService adminCinemaService;
    private final AdminCinemaMarkerDecorator markerDecorator;

    public List<AdminCinemaMarkerResponse> getAllCinemaMarker() {
        return cinemaService.getAllCinemas().stream()
            .map(markerDecorator::decorate)
            .collect(Collectors.toList());
    }

    public List<AdminCinemaRevenueResponse> getAllCinemaRevenue(
        String time
    ) {
        return adminCinemaService.getAllCinemaRevenueResponse(
            time
        );
    }

}
