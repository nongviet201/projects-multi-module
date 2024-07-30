package com.nongviet201.cinema.web.sdk.controller.view;

import com.nongviet201.cinema.core.service.MovieService;
import com.nongviet201.cinema.core.service.ShowtimeService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
public abstract class WebController {
    private final MovieService movieService;
    private final ShowtimeService showtimeService;

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
    public String getBookingPage(
            @RequestParam(value = "showtime", required = false) Integer showtimeId,
            Model model
    ) {
        if (showtimeId!= null) {
            model.addAttribute(
                "showtime",
                showtimeService.getShowtimeById(showtimeId)
            );
        }
        return "booking/booking";
    }
}
