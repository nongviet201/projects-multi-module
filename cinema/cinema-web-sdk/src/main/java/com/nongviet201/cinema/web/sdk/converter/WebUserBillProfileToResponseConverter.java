package com.nongviet201.cinema.web.sdk.converter;

import com.nongviet201.cinema.web.sdk.response.WebUserBillProfileResponse;
import org.springframework.stereotype.Service;

@Service
public class WebUserBillProfileToResponseConverter {

    public WebUserBillProfileResponse convert(
        int billId,
        int showtimeId,
        String movieName,
        String moviePoster,
        String auditoriumName,
        String cinemaName,
        String startTime,
        String screeningDate,
        String graphicsTypeAndAuditoriumType
    ) {
        return WebUserBillProfileResponse.builder()
            .billId(billId)
            .showtimeId(showtimeId)
            .movieName(movieName)
            .moviePoster(moviePoster)
            .auditoriumName(auditoriumName)
            .cinemaName(cinemaName)
            .startTime(startTime)
            .screeningDate(screeningDate)
            .graphicsTypeAndAuditoriumType(graphicsTypeAndAuditoriumType)
            .build();
    }
}
