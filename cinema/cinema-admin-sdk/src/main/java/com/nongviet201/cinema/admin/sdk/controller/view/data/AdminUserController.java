package com.nongviet201.cinema.admin.sdk.controller.view.data;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/users")
@AllArgsConstructor
public class AdminUserController {

    @GetMapping("")
    public String getUserIndexPage() {
        return "users/index";
    }
}
