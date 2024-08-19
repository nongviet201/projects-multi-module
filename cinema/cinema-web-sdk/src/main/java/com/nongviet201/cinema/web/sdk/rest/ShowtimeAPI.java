package com.nongviet201.cinema.web.sdk.rest;

import com.nongviet201.cinema.web.sdk.controller.service.WebShowtimeControllerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/showtime")
public class ShowtimeAPI {

    private final WebShowtimeControllerService showtimeControllerService;

    @RequestMapping("/get/{movieId}/{cityId}")
    public ResponseEntity<?> getShowtimeByMovieIdAndCityId(
        @PathVariable int movieId,
        @PathVariable int cityId
    ) {
        return ResponseEntity.ok(
            showtimeControllerService.getShowtimeByMovieIdAndCityId(
                    movieId,
                    cityId
            )
        );
    }

    @RequestMapping("/get/showtimeId/{showtimeId}")
    public ResponseEntity<?> getShowtimeById(
        @Valid
        @PathVariable int showtimeId
    ) {
        return ResponseEntity.ok(
                showtimeControllerService.getShowtimeById(showtimeId)
        );
    }


}