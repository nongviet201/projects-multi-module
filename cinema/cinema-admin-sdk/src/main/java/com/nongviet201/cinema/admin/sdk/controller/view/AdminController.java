package com.nongviet201.cinema.admin.sdk.controller.view;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public abstract class AdminController {

    @GetMapping("/user-details")
    public String getUserDetailsPage() {
        return "/users/detail";
    }

    @GetMapping("")
    public String getDashBoardPage() {
        return "dashboard/index";
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
