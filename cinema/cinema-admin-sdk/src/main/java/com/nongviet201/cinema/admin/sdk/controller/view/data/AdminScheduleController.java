package com.nongviet201.cinema.admin.sdk.controller.view.data;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/schedule")
public class AdminScheduleController {

    @GetMapping("")
    public String schedulePage() {return "schedule/index";}
}
