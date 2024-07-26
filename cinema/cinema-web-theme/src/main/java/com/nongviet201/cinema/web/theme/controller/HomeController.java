package com.nongviet201.cinema.web.theme.controller;

import com.nongviet201.cinema.web.sdk.controller.view.WebController;
import com.nongviet201.cinema.web.sdk.service.MovieService;
import com.nongviet201.cinema.web.sdk.service.ShowtimeService;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController extends WebController {

    public HomeController(MovieService movieService, ShowtimeService showtimeService) {
        super(movieService, showtimeService);
    }
}
