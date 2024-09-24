package com.nongviet201.cinema.admin.sdk.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("")
    public String index() {
        return "redirect:/admin";
    }

}
