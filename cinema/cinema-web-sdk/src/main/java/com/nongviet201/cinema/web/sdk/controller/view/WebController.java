package com.nongviet201.cinema.web.sdk.controller.view;

import com.nongviet201.cinema.core.service.CityService;
import com.nongviet201.cinema.core.service.ComboService;
import com.nongviet201.cinema.web.sdk.service.MovieService;
import com.nongviet201.cinema.web.sdk.service.SeatService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
public abstract class WebController {
    private final MovieService movieService;
    private final SeatService seatService;
    private final ComboService comboService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute(
            "movies",
            movieService.getAllPublishMoviesOrderByReleaseDate()
        );
        return "index";
    }

    @GetMapping("/movie/{slug}")
    public String infoMoviePage(@PathVariable("slug") String slug, Model model) {
        model.addAttribute(
            "movie",
            movieService.getPublishMovieBySlug(slug)
        );
        return "movie/detail";
    }

    @GetMapping("/booking")
    public String getBookingPage(Model model) {
        model.addAttribute(
            "seats",
            seatService.getAllSeatsByAuditoriumIdOrderBySeatColumnDesc(1)
        );
        model.addAttribute(
            "combos",
            comboService.getAllCombo()
        );
        return "booking/booking";
    }
}
