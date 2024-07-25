package com.nongviet201.cinema.web.theme.controller;

import com.nongviet201.cinema.core.service.ComboService;
import com.nongviet201.cinema.web.sdk.controller.view.WebController;
import com.nongviet201.cinema.web.sdk.service.MovieService;
import com.nongviet201.cinema.web.sdk.service.SeatService;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController extends WebController {

    public HomeController(MovieService movieService, SeatService seatService, ComboService comboService) {
        super(movieService, seatService, comboService);
    }
}
