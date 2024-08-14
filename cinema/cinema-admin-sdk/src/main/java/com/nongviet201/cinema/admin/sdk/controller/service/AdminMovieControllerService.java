package com.nongviet201.cinema.admin.sdk.controller.service;

import com.nongviet201.cinema.core.entity.movie.Genre;
import com.nongviet201.cinema.core.entity.movie.Movie;
import com.nongviet201.cinema.core.service.GenreService;
import com.nongviet201.cinema.core.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminMovieControllerService {

    private final MovieService movieService;
    private final GenreService genreService;

    public Movie getMovieById(
        Integer id
    ) {
       return movieService.getMovieById(id);
    }

    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

}
