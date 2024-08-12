package com.nongviet201.cinema.web.sdk.controller.decorator;

import com.nongviet201.cinema.core.model.entity.bill.Reservation;
import com.nongviet201.cinema.web.sdk.converter.WebReservationToResponseConverter;
import com.nongviet201.cinema.web.sdk.response.WebReservationResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WebReservationDecorator {

    private final WebReservationToResponseConverter converter;

    public WebReservationResponse decorate(
            Reservation reservation
    ) {
        return converter.convert(
                reservation.getSeat().getId(),
                reservation.getShowTime().getId(),
                reservation.getStatus().toString()
        );
    }
}
