package com.nongviet201.cinema.web.sdk.converter;

import com.nongviet201.cinema.web.sdk.response.WebReservationResponse;
import org.springframework.stereotype.Service;

@Service
public class WebReservationToResponseConverter {

    public WebReservationResponse convert(
            Integer seatId,
            Integer showtimeId,
            String status
    ) {
        return WebReservationResponse.builder()
                .seatId(seatId)
                .showtimeId(showtimeId)
                .status(status)
                .build();
    }
}
