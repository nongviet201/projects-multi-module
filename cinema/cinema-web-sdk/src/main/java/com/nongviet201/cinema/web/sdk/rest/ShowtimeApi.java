package com.nongviet201.cinema.web.sdk.rest;

import com.nongviet201.cinema.core.model.entity.cinema.Seat;
import com.nongviet201.cinema.core.model.entity.cinema.Showtime;
import com.nongviet201.cinema.web.sdk.service.ShowtimeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/showtime")
public class ShowtimeApi {
    private final ShowtimeService showtimeService;

    @RequestMapping("/get/{movieId}/{cityId}")
    public ResponseEntity<?> getShowtimeById(
        @Valid
        @PathVariable int movieId,
        @PathVariable int cityId
    ) {
        List<Showtime> showtimes =
            showtimeService.getShowtimeByMovieIdAndCityId(movieId, cityId);
        return ResponseEntity.ok(showtimes);
    }
}
