package com.nongviet201.cinema.admin.sdk.rest;

import com.nongviet201.cinema.admin.sdk.controller.service.AdminCinemaControllerService;
import com.nongviet201.cinema.admin.sdk.response.AdminCinemaMarkerResponse;
import com.nongviet201.cinema.admin.sdk.response.AdminCinemaRevenueResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/api/v1/cinema")
public class CinemaAPI {

    private final AdminCinemaControllerService cinemaControllerService;

    @GetMapping("/get/all-marker")
    public List<AdminCinemaMarkerResponse> getCinemaMarker() {
        return cinemaControllerService.getAllCinemaMarker();
    }

    @GetMapping("/get/all-revenue")
    public List<AdminCinemaRevenueResponse> getAllCinemaRevenue(
        @RequestParam(value = "time", required = false, defaultValue = "week") String time
    ) {
        return cinemaControllerService.getAllCinemaRevenue(time);
    }

}
