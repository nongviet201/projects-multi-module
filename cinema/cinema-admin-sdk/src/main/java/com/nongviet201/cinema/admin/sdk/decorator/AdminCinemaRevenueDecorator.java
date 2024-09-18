package com.nongviet201.cinema.admin.sdk.decorator;

import com.nongviet201.cinema.admin.sdk.converter.AdminCinemaRevenueToResponseConverter;
import com.nongviet201.cinema.admin.sdk.response.AdminCinemaRevenueResponse;
import com.nongviet201.cinema.core.entity.cinema.Cinema;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminCinemaRevenueDecorator {
    private final AdminCinemaRevenueToResponseConverter converter;

//    public AdminCinemaRevenueResponse decorate (
//        Cinema cinema
//    ) {
//        return converter.convert(
//            cinema.getName(),
//
//        );
//    }
}
