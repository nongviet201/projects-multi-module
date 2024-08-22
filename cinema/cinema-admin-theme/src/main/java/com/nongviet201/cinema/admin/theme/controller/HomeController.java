package com.nongviet201.cinema.admin.theme.controller;

import com.nongviet201.cinema.admin.sdk.controller.service.AdminCinemaControllerService;
import com.nongviet201.cinema.admin.sdk.controller.service.AdminDashboardControllerService;
import com.nongviet201.cinema.admin.sdk.controller.service.AdminMovieControllerService;
import com.nongviet201.cinema.admin.sdk.controller.view.AdminController;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController extends AdminController {

    public HomeController(AdminMovieControllerService adminMovieControllerService, AdminCinemaControllerService adminCinemaControllerService, AdminDashboardControllerService adminDashboardControllerService) {
        super(adminMovieControllerService, adminCinemaControllerService, adminDashboardControllerService);
    }
}
