package com.nongviet201.cinema.admin.sdk.controller.service;

import com.nongviet201.cinema.core.entity.movie.*;
import com.nongviet201.cinema.core.model.enums.movie.GraphicsType;
import com.nongviet201.cinema.core.model.enums.movie.MovieAge;
import com.nongviet201.cinema.core.model.enums.movie.TranslationType;
import com.nongviet201.cinema.core.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminMovieControllerService {

    private final ActorService actorService;
    private final MovieService movieService;
    private final DirectorService directorService;
    private final CountryService countryService;
    private final GenreService genreService;

    public void commonMovieAttributes(Model model) {
        model.addAttribute("countries", getAllCountries());
        model.addAttribute("directors", getAllDirectors());
        model.addAttribute("actors", getAllActors());
        model.addAttribute("genres", getAllGenres());
        model.addAttribute("graphicTypes", GraphicsType.values());
        model.addAttribute("translationTypes", TranslationType.values());
        model.addAttribute("movieAges", MovieAge.values());
    }

    public Movie getMovieById(
        Integer id
    ) {
       return movieService.getMovieById(id);
    }

    public List<Movie> getAllMovies() {
        return movieService.getAllMoviesOderByReleaseDate();
    }

    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    public List<Actor> getAllActors() {
        return actorService.getAllActors();
    }

    public List<Director> getAllDirectors() {
        return directorService.getAllDirectors();
    }

    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

}
