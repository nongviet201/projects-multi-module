package com.nongviet201.cinema.admin.sdk.controller.view.data;

import com.nongviet201.cinema.admin.sdk.controller.service.AdminMovieControllerService;
import com.nongviet201.cinema.core.model.enums.movie.GraphicsType;
import com.nongviet201.cinema.core.model.enums.movie.MovieAge;
import com.nongviet201.cinema.core.model.enums.movie.TranslationType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/movie")
@AllArgsConstructor
public class AdminMovieController {

    private final AdminMovieControllerService adminMovieControllerService;

    @GetMapping("")
    public String getAdminMoviePage(
        Model model
    ) {
        model.addAttribute(
            "movies",
            adminMovieControllerService.getAllMovies()
        );
        return "/movie/index";
    }

    @GetMapping("/{id}")
    public String getAdminInfoMoviePage(
        Model model,
        @PathVariable Integer id
    ) {
        model.addAttribute(
            "movie",
            adminMovieControllerService.getMovieById(id)
        );
        adminMovieControllerService.commonMovieAttributes(model);
        return "/movie/detail";
    }

    @GetMapping("/create")
    public String getAdminMovieCreatePage(
        Model model
    ) {
        adminMovieControllerService.commonMovieAttributes(model);
        return "/movie/create";
    }

}
