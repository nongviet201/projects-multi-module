package com.nongviet201.cinema.admin.theme.controller;

import com.nongviet201.cinema.admin.sdk.controller.service.AdminMovieControllerService;
import com.nongviet201.cinema.admin.sdk.controller.view.AdminController;
import com.nongviet201.cinema.core.service.*;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController extends AdminController {

    public HomeController(ActorService actorService, MovieService movieService, DirectorService directorService, CountryService countryService, GenreService genreService, AdminMovieControllerService adminMovieControllerService) {
        super(actorService, movieService, directorService, countryService, genreService, adminMovieControllerService);
    }
}
