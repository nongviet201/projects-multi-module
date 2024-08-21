package com.nongviet201.cinema.admin.sdk.controller.decorator;

import com.nongviet201.cinema.admin.sdk.converter.AdminCinemaMarkerToResponseConverter;
import com.nongviet201.cinema.admin.sdk.response.AdminCinemaMarkerResponse;
import com.nongviet201.cinema.core.entity.cinema.Cinema;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminCinemaMarkerDecorator {

    private final AdminCinemaMarkerToResponseConverter converter;

    public AdminCinemaMarkerResponse decorate (
        Cinema cinema
    ) {
        return converter.convert(
            cinema.getId(),
            cinema.getName(),
            cinema.getLat(),
            cinema.getLng(),
            cinema.getAddress()
        );
    }
}
