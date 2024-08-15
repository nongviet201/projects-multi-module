package com.nongviet201.cinema.web.sdk.converter;

import com.nongviet201.cinema.web.sdk.response.WebShowtimeResponse;
import org.springframework.stereotype.Service;

@Service
public class WebShowtimeToResponseConvert {

    public WebShowtimeResponse convert (
            int showtimeId,
            String screeningDate,
            String startTime,
            String movieName,
            String moviePoster,
            String ageRequirement,
            int auditoriumId,
            String auditoriumName,
            String auditoriumType,
            String cinemaName
    ) {
        return WebShowtimeResponse.builder()
                .id(showtimeId)
                .screeningDate(screeningDate)
                .startTime(startTime)
                .moviePoster(moviePoster)
                .movieName(movieName)
                .ageRequirement(ageRequirement)
                .auditoriumId(auditoriumId)
                .auditoriumName(auditoriumName)
                .auditoriumType(auditoriumType)
                .cinemaName(cinemaName)
                .build();
    }
}
