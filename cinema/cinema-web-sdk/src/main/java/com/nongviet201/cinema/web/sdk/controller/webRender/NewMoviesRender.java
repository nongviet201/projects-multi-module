package com.nongviet201.cinema.web.sdk.controller.webRender;

import com.nongviet201.cinema.core.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/movies")
@AllArgsConstructor
public class NewMoviesRender {
    private final MovieService movieService;
    @GetMapping("/get/new")
    public String webNewMovies(
        Model model
    ) {
        model.addAttribute(
            "newMovies",
            movieService.getAllPublishMoviesOrderByReleaseDate()
        );
        return "fragments/new-movies";
    }
}
