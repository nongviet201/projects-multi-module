package com.nongviet201.cinema.web.sdk.service;


import com.nongviet201.cinema.core.model.entity.movie.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllPublishMoviesOrderByReleaseDate();
    Movie getPublishMovieBySlug(String slug);
    Movie findById(int id);
}
