package com.nongviet201.cinema.web.theme.controller;

import com.nongviet201.cinema.core.service.MovieService;
import com.nongviet201.cinema.core.service.PostService;
import com.nongviet201.cinema.web.sdk.controller.service.*;
import com.nongviet201.cinema.web.sdk.controller.view.WebController;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController extends WebController {
    public HomeController(MovieService movieService, WebShowtimeControllerService webShowtimeControllerService, WebBillControllerService webBillControllerService, WebUserControllerService webUserControllerService, WebVerifyService verifyService, WebPostsControllerService webPostsControllerService) {
        super(movieService, webShowtimeControllerService, webBillControllerService, webUserControllerService, verifyService, webPostsControllerService);
    }
}
