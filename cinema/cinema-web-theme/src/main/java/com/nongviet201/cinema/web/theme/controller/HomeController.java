package com.nongviet201.cinema.web.theme.controller;

import com.nongviet201.cinema.core.service.BillService;
import com.nongviet201.cinema.core.service.MovieService;
import com.nongviet201.cinema.core.service.ShowtimeService;
import com.nongviet201.cinema.web.sdk.controller.service.WebBillControllerService;
import com.nongviet201.cinema.web.sdk.controller.view.WebController;

import org.springframework.stereotype.Controller;

@Controller
public class HomeController extends WebController {

    public HomeController(MovieService movieService, ShowtimeService showtimeService, WebBillControllerService billControllerService) {
        super(movieService, showtimeService, billControllerService);
    }
}
