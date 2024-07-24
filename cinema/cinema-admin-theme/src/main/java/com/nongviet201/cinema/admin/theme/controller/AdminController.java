package com.nongviet201.cinema.admin.theme.controller;


import com.nongviet201.cinema.core.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private final ActorService actorService;
    private final MovieService movieService;
    private final DirectorService directorService;
    private final CountryService countryService;
    private final GenreService genreService;

    @GetMapping("")
    public String getDashBoardPage() {
        return "index";
    }

    @GetMapping("/user-details")
    public String getUserDetailsPage() {
        return "user/detail";
    }

    @GetMapping("/movies")
    public String getAdminMoviePage(
        Model model
    ) {
        model.addAttribute(
            "movies",
            movieService.getAll()
        );
        return "movie/index";
    }

    @GetMapping("/movie/{id}")
    public String getAdminInfoMoviePage(
        Model model,
        @PathVariable
        int id) {
        model.addAttribute(
            "movie",
            movieService.findById(id)
        );
        return "movie/detail";
    }

    @GetMapping("/movie/create")
    public String getAdminMovieCreatePage(
        Model model
    ) {
        model.addAttribute("countries", countryService.getAllCountries());
        model.addAttribute("directors", directorService.getAllDirectors());
        model.addAttribute("actors", actorService.getAllActors());
        model.addAttribute("genres", genreService.getAllGenres());
        return "movie/create";
    }


}
