package com.nongviet201.cinema.admin.sdk.controller.service;

import com.nongviet201.cinema.admin.sdk.controller.decorator.AdminCinemaMarkerDecorator;
import com.nongviet201.cinema.admin.sdk.response.AdminCinemaMarkerResponse;
import com.nongviet201.cinema.admin.sdk.response.AdminCinemaRevenueResponse;
import com.nongviet201.cinema.admin.sdk.service.AdminAuditoriumService;
import com.nongviet201.cinema.admin.sdk.service.AdminCinemaService;
import com.nongviet201.cinema.admin.sdk.service.AdminSeatService;
import com.nongviet201.cinema.core.entity.cinema.Auditorium;
import com.nongviet201.cinema.core.entity.cinema.Cinema;
import com.nongviet201.cinema.core.service.CinemaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminCinemaControllerService {

    private final AdminCinemaService adminCinemaService;
    private final AdminAuditoriumService adminAuditoriumService;
    private final AdminCinemaMarkerDecorator markerDecorator;
    private final AdminSeatService adminSeatService;

    public List<Cinema> getAllCinema() {
        return adminCinemaService.getAllCinema();
    }

    public List<Cinema> getAllCinemaDeleted() {
        return adminCinemaService.getAllDeletedCinema();
    }

    public void getCinemaDetailById(
        int id,
        Model model
    ) {
        model.addAttribute("cinema", adminCinemaService.getCinemaById(id));
        model.addAttribute("auditoriums", adminAuditoriumService.getAllAuditoriumByCinemaId(id));
        model.addAttribute("cities", adminCinemaService.getAllCities());
        model.addAttribute("audType", adminAuditoriumService.getAllAuditoriumTypes());
        model.addAttribute("seatTypes", adminSeatService.getAllSeatTypes());
    }

    public List<AdminCinemaMarkerResponse> getAllCinemaMarker() {
        return adminCinemaService.getAllCinema().stream()
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


    public List<Auditorium> getAllAuditoriumDeleted() {
        return adminAuditoriumService.getAllDeletedAuditorium();
    }
}
