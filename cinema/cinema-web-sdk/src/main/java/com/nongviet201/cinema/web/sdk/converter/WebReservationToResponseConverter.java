package com.nongviet201.cinema.web.sdk.converter;

import com.nongviet201.cinema.web.sdk.response.WebReservationResponse;
import org.springframework.stereotype.Service;

@Service
public class WebReservationToResponseConverter {

    public WebReservationResponse convert(
        Integer seatId,
        Integer showtimeId,
        String status,
        Boolean isCurrentUser,
        Long timeRemaining
    ) {
        return WebReservationResponse.builder()
            .seatId(seatId)
            .showtimeId(showtimeId)
            .status(status)
            .isCurrentUser(isCurrentUser)
            .timeRemaining(timeRemaining)
            .build();
    }
}
