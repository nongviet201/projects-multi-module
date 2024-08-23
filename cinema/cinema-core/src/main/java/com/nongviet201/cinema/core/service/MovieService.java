package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.movie.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllPublishMoviesOrderByRating();

    Movie getPublishMovieBySlug(String slug);

    List<Movie> getAllMoviesOderByReleaseDate();

    Movie getMovieById(int id);

    Movie getPublishMovieById(int id);

    double updateRating(int movieId, int ratting);

    List<Movie> getAllDeletedMovie();
}
