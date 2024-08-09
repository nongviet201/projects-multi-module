package com.nongviet201.cinema.web.theme.controller;

import com.nongviet201.cinema.core.service.BillService;
import com.nongviet201.cinema.core.service.MovieService;
import com.nongviet201.cinema.core.service.PostService;
import com.nongviet201.cinema.core.service.ShowtimeService;
import com.nongviet201.cinema.web.sdk.controller.service.WebBillControllerService;
import com.nongviet201.cinema.web.sdk.controller.service.WebShowtimeControllerService;
import com.nongviet201.cinema.web.sdk.controller.service.WebUserControllerService;
import com.nongviet201.cinema.web.sdk.controller.service.WebVerifyService;
import com.nongviet201.cinema.web.sdk.controller.view.WebController;

import org.springframework.stereotype.Controller;

@Controller
public class HomeController extends WebController {

    public HomeController(MovieService movieService, WebShowtimeControllerService showtimeControllerService, WebBillControllerService billControllerService, WebVerifyService verifyService, PostService postService) {
        super(movieService, showtimeControllerService, billControllerService, verifyService, postService);
    }
}
