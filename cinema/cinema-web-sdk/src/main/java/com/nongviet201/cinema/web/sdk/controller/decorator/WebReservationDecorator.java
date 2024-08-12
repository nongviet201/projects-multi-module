package com.nongviet201.cinema.web.sdk.controller.decorator;

import com.nongviet201.cinema.core.entity.bill.Reservation;
import com.nongviet201.cinema.core.service.UserService;
import com.nongviet201.cinema.web.sdk.converter.WebReservationToResponseConverter;
import com.nongviet201.cinema.web.sdk.response.WebReservationResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WebReservationDecorator {

    private final WebReservationToResponseConverter converter;
    private final UserService userService;
    private final WebDateTimeFormatter dateTimeFormatter;

    public WebReservationResponse decorate(
        Reservation reservation
    ) {
        return converter.convert(
            reservation.getSeat().getId(),
            reservation.getShowtime().getId(),
            reservation.getStatus().toString(),
            userService.isCurrentUser(reservation.getUser()),
            dateTimeFormatter.calculateRemainingMillis(reservation.getStartOrderTime())
        );
    }
}
