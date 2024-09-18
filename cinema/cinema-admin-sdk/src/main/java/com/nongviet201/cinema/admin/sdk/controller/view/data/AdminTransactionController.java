package com.nongviet201.cinema.admin.sdk.controller.view.data;

import com.nongviet201.cinema.admin.sdk.controller.service.AdminCinemaControllerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/transaction")
@AllArgsConstructor
public class AdminTransactionController {
    private final AdminCinemaControllerService adminCinemaControllerService;

    @GetMapping("")
    public String getTransactionPage(
        Model model
    ) {
        model.addAttribute(
            "cinema",
            adminCinemaControllerService.getAllCinema()
        );
        return "transaction/index";
    }
}
