package com.nongviet201.cinema.core.service;



import com.nongviet201.cinema.core.model.entity.movie.Movie;
import com.nongviet201.cinema.core.model.request.UpsertMovieRequest;

import java.util.List;

public interface MovieService {
    Movie createMovie(UpsertMovieRequest movieRequest);

    List<Movie> getAllPublishMoviesOrderByReleaseDate();
    List<Movie> getAll();
    Movie getPublishMovieBySlug(String slug);
    Movie findById(int id);
}
