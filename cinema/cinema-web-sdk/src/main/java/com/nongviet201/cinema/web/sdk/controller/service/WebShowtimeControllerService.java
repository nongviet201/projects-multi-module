package com.nongviet201.cinema.web.sdk.controller.service;

import com.nongviet201.cinema.core.entity.cinema.Showtime;
import com.nongviet201.cinema.core.service.ShowtimeService;
import com.nongviet201.cinema.web.sdk.controller.decorator.WebShowtimeDecorator;
import com.nongviet201.cinema.web.sdk.response.WebShowtimeResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WebShowtimeControllerService {

    private final ShowtimeService showtimeService;
    private final WebShowtimeDecorator showtimeDecorator;

    public List<WebShowtimeResponse> getShowtimeByMovieIdAndCityId(
            int movieId,
            int cityId
    ) {
        List<Showtime> showTimes = showtimeService.getShowtimeByMovieIdAndCityId(movieId, cityId);
        return showTimes.stream()
                .map(showtimeDecorator::decorate)
                .collect(Collectors.toList());
    }

    public WebShowtimeResponse getShowtimeById (
            int showtimeId
    ) {
        return showtimeDecorator.decorate(showtimeService.getShowtimeById(showtimeId));
    }

    public List<WebShowtimeResponse> getAllShowTimesByMovieIdAndDate(
            int id,
            LocalDate date
    ) {
        List<Showtime> showTimes = showtimeService.getAllShowtimesByMovieIdAnDate(id, date);
        return showTimes.stream()
                .map(showtimeDecorator::decorate)
                .collect(Collectors.toList());
    }

    public List<WebShowtimeResponse> getAllShowtimesOnTheSameDayById(
            Integer showtimeId
    ) {
        List<Showtime> showTimes = showtimeService.getAllShowtimesOnTheSameDayById(showtimeId);
        return showTimes.stream()
                .map(showtimeDecorator::decorate)
                .collect(Collectors.toList());
    }
}
