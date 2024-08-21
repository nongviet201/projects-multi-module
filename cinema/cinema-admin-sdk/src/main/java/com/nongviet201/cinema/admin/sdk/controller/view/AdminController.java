package com.nongviet201.cinema.admin.sdk.controller.view;


import com.nongviet201.cinema.admin.sdk.controller.service.AdminCinemaControllerService;
import com.nongviet201.cinema.admin.sdk.controller.service.AdminDashboardControllerService;
import com.nongviet201.cinema.admin.sdk.controller.service.AdminMovieControllerService;
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
public abstract class AdminController {
    private final ActorService actorService;
    private final MovieService movieService;
    private final DirectorService directorService;
    private final CountryService countryService;
    private final GenreService genreService;

    private final AdminMovieControllerService adminMovieControllerService;
    private final AdminCinemaControllerService adminCinemaControllerService;
    private final AdminDashboardControllerService adminDashboardControllerService;

    @GetMapping("")
    public String getDashBoardPage(
        Model model
    ) {
        return "index";
    }

    @GetMapping("/user-details")
    public String getUserDetailsPage() {
        return "/user/detail";
    }

    @GetMapping("/movies")
    public String getAdminMoviePage(
        Model model
    ) {
        model.addAttribute(
            "movies",
            movieService.getAllMoviesOderByReleaseDate()
        );
        return "/movie/index";
    }

    @GetMapping("/movie/{id}")
    public String getAdminInfoMoviePage(
        Model model,
        @PathVariable Integer id
    ) {
        model.addAttribute(
            "movie",
            adminMovieControllerService.getMovieById(id)
        );
        return "/movie/detail";
    }

    @GetMapping("/movie/create")
    public String getAdminMovieCreatePage(
        Model model
    ) {
        model.addAttribute("countries", countryService.getAllCountries());
        model.addAttribute("directors", directorService.getAllDirectors());
        model.addAttribute("actors", actorService.getAllActors());
        model.addAttribute("genres", genreService.getAllGenres());
        return "/movie/create";
    }

    @GetMapping("/web/settings/banner")
    public String getAdminWebSettingsBannerPage(
    ) {
        return "/web-settings/banner";
    }

    @GetMapping("/web/settings/theme")
    public String getAdminWebSettingsThemePage(
    ) {
        return "/web-settings/theme";
    }

}
