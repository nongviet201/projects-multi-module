package com.nongviet201.cinema.web.theme.controller.webRender;

import com.nongviet201.cinema.core.service.CityService;
import com.nongviet201.cinema.web.sdk.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/booking/get")
@AllArgsConstructor
public class BookingRenderRename {
    private final MovieService movieService;
    private final CityService cityService;

    @GetMapping("/stage-one")
    public String getStageOneFragments(Model model) {
        model.addAttribute(
                "cities",
                cityService.getAllCities()
        );
        model.addAttribute(
                "movies",
                movieService.getAllPublishMoviesOrderByReleaseDate()
        );
        return "booking/stage/stage-one";
    }
}
