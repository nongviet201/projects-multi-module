package com.nongviet201.cinema.admin.sdk.controller.view.data;

import com.nongviet201.cinema.admin.sdk.controller.service.AdminCinemaControllerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/cinema")
@AllArgsConstructor
public class AdminCinemaController {

    private final AdminCinemaControllerService adminCinemaControllerService;

    @GetMapping("")
    public String getAdminCinemaPage(
        @RequestParam(
            value = "data",
            defaultValue = "all"
        ) String data,
        Model model
    ) {
        if (data.equals("delete")) {
            model.addAttribute(
                "cinemas",
                adminCinemaControllerService.getAllCinemaDeleted()
            );
        } else {
            model.addAttribute(
                "cinemas",
                adminCinemaControllerService.getAllCinema()
            );
        }
        return "/cinema/index";
    }

    @GetMapping("/{id}")
    public String getAdminInfoCinemaPage(
        Model model,
        @PathVariable Integer id
    ) {
        adminCinemaControllerService.getCinemaDetailById(id, model);
        return "/cinema/detail";
    }

    @GetMapping("/create")
    public String getAdminCinemaCreatePage() {
        return "/cinema/create";
    }

    @GetMapping("/aud-deleted")
    public String getAuditoriumDeletedPage(
        Model model
    ) {
        model.addAttribute(
            "auditoriumsDeleted",
            adminCinemaControllerService.getAllAuditoriumDeleted()
        );
        return "/cinema/aud-index";
    }
}
