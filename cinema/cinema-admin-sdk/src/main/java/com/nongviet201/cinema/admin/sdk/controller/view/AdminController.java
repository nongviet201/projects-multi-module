package com.nongviet201.cinema.admin.sdk.controller.view;


import com.nongviet201.cinema.admin.sdk.controller.service.AdminCinemaControllerService;
import com.nongviet201.cinema.admin.sdk.controller.service.AdminDashboardControllerService;
import com.nongviet201.cinema.admin.sdk.controller.service.AdminMovieControllerService;
import com.nongviet201.cinema.core.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public abstract class AdminController {
    private final AdminMovieControllerService adminMovieControllerService;
    private final AdminCinemaControllerService adminCinemaControllerService;
    private final AdminDashboardControllerService adminDashboardControllerService;

    @GetMapping("")
    public String getDashBoardPage(
        Model model
    ) {
        return "index";
    }

    @GetMapping("/user-details")
    public String getUserDetailsPage() {
        return "/user/detail";
    }



    @GetMapping("/web/settings/banner")
    public String getAdminWebSettingsBannerPage(
    ) {
        return "/web-settings/banner";
    }

    @GetMapping("/web/settings/theme")
    public String getAdminWebSettingsThemePage(
    ) {
        return "/web-settings/theme";
    }

}
