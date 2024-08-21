package com.nongviet201.cinema.admin.sdk.converter;

import com.nongviet201.cinema.admin.sdk.response.AdminCinemaMarkerResponse;
import org.springframework.stereotype.Service;

@Service
public class AdminCinemaMarkerToResponseConverter {

    public AdminCinemaMarkerResponse convert (
        int id,
        String name,
        double lat,
        double lng,
        String address
    ) {
        return AdminCinemaMarkerResponse.builder()
            .id(id)
            .name(name)
            .lat(lat)
            .lng(lng)
            .address(address)
            .build();
    }
}
