package com.nongviet201.cinema.admin.sdk.controller.view.data;

import com.nongviet201.cinema.core.model.enums.movie.MovieScheduleStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/schedule")
@AllArgsConstructor
public class AdminScheduleController {

    @GetMapping("")
    public String schedulePage(
        Model model
    ) {
       model.addAttribute(
           "status",
           List.of(MovieScheduleStatus.values())
       );
        return "schedule/index";
    }
}
