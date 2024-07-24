package com.nongviet201.cinema.admin.sdk.service;

import com.nongviet201.cinema.core.model.entity.movie.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMoviesOderByReleaseDate();
    Movie getMovieById(int id);
}
