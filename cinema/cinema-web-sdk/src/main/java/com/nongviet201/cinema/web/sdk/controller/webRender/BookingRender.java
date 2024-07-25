package com.nongviet201.cinema.web.sdk.controller.webRender;

import com.nongviet201.cinema.core.service.CityService;
import com.nongviet201.cinema.web.sdk.service.MovieService;
import com.nongviet201.cinema.web.sdk.service.SeatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/booking/get")
@AllArgsConstructor
public class BookingRender {
    private final MovieService movieService;
    private final CityService cityService;
    private final SeatService seatService;

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

    @GetMapping("/stage-two")
    public String getStageTwoFragments(
        @RequestParam(value = "auditoriumId") int id,
        Model model
    ) {
        model.addAttribute(
            "seats",
            seatService.getAllSeatsByAuditoriumIdOrderBySeatColumnDesc(id)
        );
        return "booking/stage/stage-two";
    }
}
