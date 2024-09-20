package com.nongviet201.cinema.admin.sdk.controller.view.data;

import com.nongviet201.cinema.admin.sdk.controller.service.AdminCinemaControllerService;
import com.nongviet201.cinema.admin.sdk.controller.service.AdminTransactionControllerService;
import com.nongviet201.cinema.admin.sdk.service.AdminTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/transaction")
@AllArgsConstructor
public class AdminTransactionController {
    private final AdminCinemaControllerService adminCinemaControllerService;
    private final AdminTransactionControllerService adminTransactionControllerService;

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

    @GetMapping("/bill/{id}")
    public String getBillPage(
        Model model,
        @PathVariable Integer id
    ) {
        model.addAttribute(
            "bill",
            adminTransactionControllerService.getBillById(id)
        );
        return "transaction/bill";
    }
}
