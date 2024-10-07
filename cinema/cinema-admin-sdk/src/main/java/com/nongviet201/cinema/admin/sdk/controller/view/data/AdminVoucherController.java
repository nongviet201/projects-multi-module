package com.nongviet201.cinema.admin.sdk.controller.view.data;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/voucher")
public class AdminVoucherController {

    @GetMapping("")
    public String adminVoucherPage(

    ) {
        return "voucher/index";
    }
}
