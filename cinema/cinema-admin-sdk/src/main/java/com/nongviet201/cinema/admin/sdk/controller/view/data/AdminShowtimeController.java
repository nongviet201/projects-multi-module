package com.nongviet201.cinema.admin.sdk.controller.view.data;

import com.nongviet201.cinema.admin.sdk.controller.service.AdminShowtimeControllerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/showtime")
@AllArgsConstructor
public class AdminShowtimeController {

    private final AdminShowtimeControllerService adminShowtimeControllerService;

    @GetMapping("")
    public String showtimePage(
        Model model
    ) {
        adminShowtimeControllerService.commonShowtimeAttributes(model);
        return "showtime/index";
    }
}
